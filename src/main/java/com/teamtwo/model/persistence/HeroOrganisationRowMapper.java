package com.teamtwo.model.persistence;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;


import com.teamtwo.dto.entity.HeroOrganisation;

public class HeroOrganisationRowMapper implements RowMapper<HeroOrganisation> {

	@Override
	public HeroOrganisation mapRow(ResultSet resultset, int rowNum) throws SQLException {
		HeroOrganisation heroOrganisation=new HeroOrganisation();
		
        heroOrganisation.setSerialId(resultset.getInt("serialId"));
        heroOrganisation.setHeroId(resultset.getInt("heroId"));
        heroOrganisation.setOrgId(resultset.getInt("orgId"));
  
		
		return heroOrganisation;
	}

}