package com.teamtwo.model.persistence;

import java.sql.Date;
import java.util.List;

import com.teamtwo.dto.entity.Sighting;


public interface SightingDao {

	public Sighting getSightingById(int sightingId);
	public List<Sighting> getAllSighting();
	public int editSighting(Sighting sighting);
	public int addSighting(Sighting sighting);
	public int deleteSighting(int sightingId);
	List <Sighting> getSightingByDate(Date locationDate);
	List <Sighting> getSightingByLocationId(int locationId);
	public List<Sighting> findLatestSightings();
}
