package com.teamtwo.model.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.teamtwo.dto.entity.Organisation;
import com.teamtwo.model.persistence.OrganisationDao;


@Service
public class OrganisationServiceImpl implements OrganisationService {

	@Autowired
	private OrganisationDao organisationDao;
	
	
	
	@Override
	public Organisation searchOrganisationById(int orgId) {
		return organisationDao.getOrganisationById(orgId);
	}

	@Override
	public List<Organisation> getAllOrganisations() {
		return organisationDao.getAllOrganisations();
	}

	@Override
	public boolean insertOrganisation(Organisation organisation) {
		return organisationDao.addOrganisation(organisation)>0;
	}

	@Override
	public Organisation deleteOrganisation(int orgId) {
		Organisation organisation=searchOrganisationById(orgId);
		
		if(organisation!=null)
			organisationDao.deleteOrganisation(orgId);
		return organisation;
	}

	@Override
	public Organisation updateOrganisation(Organisation organisation) {
	    Organisation existingOrganisation = searchOrganisationById(organisation.getOrgId());

	    if (existingOrganisation != null) {
	        existingOrganisation.setOrgName(organisation.getOrgName()); 
	        existingOrganisation.setOrgDesc(organisation.getOrgDesc()); 
	        existingOrganisation.setOrgAddress(organisation.getOrgAddress()); 
	       

	        organisationDao.editOrganisation(existingOrganisation);
	        return existingOrganisation;
	    }

	    return null;
	}



}
