package com.teamtwo.model.service;

import java.util.List;

import com.teamtwo.dto.entity.Location;

public interface LocationService {

	public Location searchLocationById(int locationId);
	 public List<Location> getAllLocations();
	 public boolean insertLocation(Location location);
	 public Location updateLocation(Location location);
	 public Location deleteLocation(int locationid);
}
