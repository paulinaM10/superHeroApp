package com.teamtwo.model.persistence;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.teamtwo.dto.entity.Location;

@Repository
public class LocationDaoImpl implements LocationDao {

	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	@Override
	public Location getLocationById(int locationId) {
		
		return jdbcTemplate.queryForObject("SELECT * FROM Location WHERE locationId=?", new LocationRowMapper(), locationId);
	}

	@Override
	public List<Location> getAllLocations() {
		
		return jdbcTemplate.query("SELECT * FROM Location", new LocationRowMapper());
	}

	@Override
	public int editLocation(Location location) {
		
		return jdbcTemplate.update("UPDATE Location SET locationName = ?, locationDesc = ?, locationAddress = ?, coordinates = ?WHERE locationId = ?", location.getLocationName(), location.getLocationDesc(), 
              location.getLocationAddress(), location.getCoordinates(), location.getLocationId());
	}

	@Override
	public int addLocation(Location location) {
		
		return jdbcTemplate.update("INSERT INTO Location VALUES (?, ?, ?, ?, ?) ",location.getLocationId(), location.getLocationName(), location.getLocationDesc(), 
	              location.getLocationAddress(), location.getCoordinates());

	}

	@Override
	public int deleteLocation(int locationId) {
		String sql = "DELETE FROM Location WHERE locationId = ?";

        return jdbcTemplate.update(sql, locationId);
	}

}