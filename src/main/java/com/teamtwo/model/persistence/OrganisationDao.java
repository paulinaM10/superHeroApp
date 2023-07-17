package com.teamtwo.model.persistence;

import java.util.List;

import com.teamtwo.dto.entity.Organisation;

public interface OrganisationDao {

	public Organisation getOrganisationById(int orgId);
	public List<Organisation> getAllOrganisations();
	public int editOrganisation(Organisation organisation);
	public int addOrganisation(Organisation organisation);
	public int deleteOrganisation(int orgId);
	
}
