package com.teamtwo.model.persistence;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.teamtwo.dto.entity.Organisation;


@Repository
public class OrganisationDaoImpl implements OrganisationDao {

	
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	
	@Override
	public Organisation getOrganisationById(int orgId) {
	    String sql = "SELECT * FROM Organisation WHERE orgId = ?";

	    try {
	        return jdbcTemplate.queryForObject(sql, new OrganisationRowMapper(), orgId);
	    } catch (EmptyResultDataAccessException e) {
	        
	        return null; 
	    }
	}


	 @Override
	    public List<Organisation> getAllOrganisations() {
	        String sql = "SELECT * FROM Organisation";

	        return jdbcTemplate.query(sql, new OrganisationRowMapper());
	    }

	 @Override
	    public int editOrganisation(Organisation organisation) {
	        String sql = "UPDATE Organisation SET orgName = ?, orgDesc = ?, orgAddress = ? WHERE orgId = ?";

	        return jdbcTemplate.update(sql, organisation.getOrgName(), organisation.getOrgDesc(), 
	                organisation.getOrgAddress(), organisation.getOrgId());
	    }

	 @Override
	    public int addOrganisation(Organisation organisation) {
	        String sql = "INSERT INTO Organisation (orgId, orgName, orgDesc, orgAddress) VALUES (?,?, ?, ?)";

	        return jdbcTemplate.update(sql, organisation.getOrgId(), organisation.getOrgName(), organisation.getOrgDesc(), 
	                organisation.getOrgAddress());
	    }

	 @Override
	    public int deleteOrganisation(int orgId) {
	        String sql = "DELETE FROM Organisation WHERE orgId = ?";

	        return jdbcTemplate.update(sql, orgId);
	    }

}
