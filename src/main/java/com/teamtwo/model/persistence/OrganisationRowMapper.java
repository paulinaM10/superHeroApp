package com.teamtwo.model.persistence;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.teamtwo.dto.entity.Organisation;

public class OrganisationRowMapper implements RowMapper<Organisation> {


	
	@Override
	public Organisation mapRow(ResultSet resultset, int rowNum) throws SQLException {
         Organisation organisation=new Organisation();
		
         organisation.setOrgId(resultset.getInt("orgId"));
         organisation.setOrgName(resultset.getString("orgName"));
         organisation.setOrgDesc(resultset.getString("orgDesc"));
         organisation.setOrgAddress(resultset.getString("orgAddress"));
		
		return organisation;
	}

}
