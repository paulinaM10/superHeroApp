package com.teamtwo.model.persistence;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.teamtwo.dto.entity.Location;

public class LocationRowMapper implements RowMapper<Location> {

	@Override
	public Location mapRow(ResultSet resultset, int rowNum) throws SQLException {
		Location location = new Location ();
		
		 location.setLocationId(resultset.getInt("locationId"));
		 location.setLocationName(resultset.getString("locationName"));
		 location.setLocationDesc(resultset.getString("locationDesc"));
		 location.setLocationAddress(resultset.getString("locationAddress"));
		 location.setCoordinates(resultset.getString("coordinates"));
		 
		return location;
	}

}
