package com.teamtwo.model.service;
import java.util.ArrayList;

import java.sql.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.teamtwo.dto.entity.Hero;
import com.teamtwo.dto.entity.Sighting;
import com.teamtwo.model.persistence.SightingDao;

@Service
public class SightingServiceImpl implements SightingService {
	
	@Autowired
	private SightingDao sightingDao;
	
	@Autowired
	private HeroService heroService;

	@Override
	public List<Sighting> getAllSightings() {
		return sightingDao.getAllSighting();
	}

	@Override
	public Sighting deleteSightingById(int sightingId) {
		Sighting sighting = searchSightingById(sightingId);
		
		if (sighting!=null) 
			sightingDao.deleteSighting(sightingId);
		
		return sighting;
		
	}

	@Override
	public Sighting searchSightingById(int sightingId) {
		Sighting sighting = sightingDao.getSightingById(sightingId);
		
                // Fetch the associated Hero
                if (sighting != null) {
                    Hero hero = heroService.searchHeroById(sighting.getHeroId());
                    sighting.setHero(hero);
                }

		return sighting;
	}

	@Override
	public Sighting updateSighting(Sighting sighting) {
		Sighting existingSighting= searchSightingById (sighting.getSightingId());
		
		if (existingSighting != null ) {
			existingSighting.setHeroId(sighting.getHeroId());
			existingSighting.setLocationId(sighting.getLocationId());
			existingSighting.setLocationDate(sighting.getLocationDate());
			
			sightingDao.addSighting(existingSighting);
			return existingSighting;
	    
	}
		return null;
	}


	@Override
	public boolean insertSighting(Sighting sighting) {
		return sightingDao.addSighting(sighting)>0;
	}

	@Override
	public List<Sighting> getSightingByDate(Date locationDate) {
		return sightingDao.getSightingByDate(locationDate);
	}

	@Override
	public List<Sighting> getSightingByLocationId(int locationId) {
	    List<Sighting> sightings = sightingDao.getSightingByLocationId(locationId);
	    if(sightings == null) {
	        sightings = new ArrayList<>();
	    }
	    return sightings;
	}


	@Override
	public List<Sighting> findLatestSightings() {
	    return sightingDao.findLatestSightings();
	}

}



