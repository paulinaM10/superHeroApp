package com.teamtwo.model.service;

import java.sql.Date;
import java.util.List;

import com.teamtwo.dto.entity.Sighting;

public interface SightingService {
	
	public List<Sighting> getAllSightings();
	public Sighting deleteSightingById (int sightingId);
	public Sighting searchSightingById (int sightingId);
	public Sighting updateSighting (Sighting sighting);
	public boolean insertSighting (Sighting sighting);
	List <Sighting> getSightingByDate (Date locationDate);
	List <Sighting> getSightingByLocationId (int locationId);
	public List<Sighting> findLatestSightings();

}