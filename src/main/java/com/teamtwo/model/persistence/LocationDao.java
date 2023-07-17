package com.teamtwo.model.persistence;

import java.util.List;

import com.teamtwo.dto.entity.Location;

public interface LocationDao {

	public Location getLocationById(int locationId);
	public List<Location> getAllLocations();
	public int editLocation(Location location);
	public int addLocation(Location location);
	public int deleteLocation(int locationId);
}
