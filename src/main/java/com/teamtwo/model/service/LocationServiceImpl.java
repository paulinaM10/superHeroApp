package com.teamtwo.model.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.teamtwo.dto.entity.Location;
import com.teamtwo.model.persistence.LocationDao;

@Service
public class LocationServiceImpl implements LocationService {

	@Autowired
	LocationDao locationDao;
	
	@Override
	public Location searchLocationById(int locationId) {
		return locationDao.getLocationById(locationId);
	}

	@Override
	public List<Location> getAllLocations() {
		return locationDao.getAllLocations();
	}

	@Override
	public boolean insertLocation(Location location) {
		return locationDao.addLocation(location)>0;
	}

	@Override
	public Location updateLocation(Location location) {
		   Location existingLocation = searchLocationById(location.getLocationId());

		    if (existingLocation != null) {
		    	existingLocation.setLocationName(location.getLocationName()); 
		    	existingLocation.setLocationDesc(location.getLocationDesc()); 
		    	existingLocation.setLocationAddress(location.getLocationAddress()); 
		    	existingLocation.setCoordinates(location.getCoordinates()); 
		       

		       locationDao.editLocation(existingLocation);
		        return existingLocation;
		    }

		    return null;
	}
	
	@Override
	public Location deleteLocation(int locationId) {
		Location location=searchLocationById(locationId);
		
		if(location!=null)
			locationDao.deleteLocation(locationId);
		return location;
	}

}
