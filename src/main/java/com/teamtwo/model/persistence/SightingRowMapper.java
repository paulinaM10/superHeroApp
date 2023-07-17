package com.teamtwo.model.persistence;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.teamtwo.dto.entity.Sighting;

public class SightingRowMapper implements RowMapper<Sighting> {

	@Override
	public Sighting mapRow(ResultSet resultSet, int rowNum) throws SQLException {
			Sighting sighting = new Sighting();
			
			sighting.setSightingId(resultSet.getInt("sightingId"));
			sighting.setHeroId(resultSet.getInt("heroId"));
			sighting.setLocationId(resultSet.getInt("locationId"));
			sighting.setLocationDate(resultSet.getDate("locationDate"));

			return sighting;
	}

}
