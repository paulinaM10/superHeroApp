package com.teamtwo.model.persistence;
import java.util.ArrayList;
import java.sql.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.teamtwo.dto.entity.Hero;
import com.teamtwo.dto.entity.Sighting;

@Repository
public class SightingDaoImpl implements SightingDao {
	
	@Autowired
	private JdbcTemplate jdbcTemplate;
	@Autowired
	private HeroDao heroDao;

	@Override
	public Sighting getSightingById(int sightingId) {
		  Sighting sighting = jdbcTemplate.queryForObject("SELECT * FROM Sighting WHERE sightingId=?", new SightingRowMapper(), sightingId);
		    
		    if (sighting != null) {
		        Hero hero = heroDao.getHeroById(sighting.getHeroId());
		        sighting.setHero(hero);
		    }

		    return sighting;
		}

	
	

	@Override
	public List<Sighting> getAllSighting() {
		return jdbcTemplate.query("SELECT * FROM Sighting", new SightingRowMapper());
	}

	@Override
	public int editSighting(Sighting sighting) {
		return jdbcTemplate.update("UPDATE Sighting SET heroId = ?, locationId = ?, locationDate = ? WHERE sightingId = ?", sighting.getHeroId(), sighting.getLocationId(), sighting.getLocationDate(), sighting.getSightingId());
	}
	
	@Override
	public int addSighting(Sighting sighting) {
		
		return jdbcTemplate.update("INSERT INTO Sighting VALUES (?, ?, ?, ?) ", sighting.getSightingId(), sighting.getHeroId(), sighting.getLocationId(), sighting.getLocationDate());
	}

	@Override
	public int deleteSighting(int sightingId) {
	    
		String sql = "DELETE FROM Sighting WHERE sightingId = ?";
	    return jdbcTemplate.update(sql, sightingId);
	}


	@Override
	public List<Sighting> getSightingByDate(Date locationDate) {
	    java.sql.Date sqlDate = new java.sql.Date(locationDate.getTime());
	    return jdbcTemplate.query("SELECT * FROM Sighting WHERE locationDate= ? ", new SightingRowMapper(), sqlDate);
	}


	@Override
	public List<Sighting> getSightingByLocationId(int locationId) {
	    List<Sighting> sightings = jdbcTemplate.query(
	        "SELECT * FROM Sighting WHERE locationId= ?",
	        new SightingRowMapper(),
	        locationId
	    );
	    
	    if (sightings == null) {
	        return new ArrayList<>();
	    }
	    
	    return sightings;
	}

	
	@Override
	public List<Sighting> findLatestSightings() {
	    final String sql = "SELECT * FROM Sighting ORDER BY locationDate DESC LIMIT 10";
	    return jdbcTemplate.query(sql, new SightingRowMapper());
	}

	

	

}
