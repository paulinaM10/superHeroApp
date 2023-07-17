package com.teamtwo.model.persistence;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.teamtwo.dto.entity.SuperPower;

public class SuperPowerRowMapper implements RowMapper<SuperPower> {

	@Override
	public SuperPower mapRow(ResultSet resultset, int rowNum) throws SQLException {
	SuperPower superPower=new SuperPower();
		
       superPower.setSuperPowerId(resultset.getInt("superPowerId"));
       superPower.setSuperPowerName(resultset.getString("superPowerName"));
       superPower.setHeroId(resultset.getInt("heroId"));
  
		
		return superPower;
	}

}
