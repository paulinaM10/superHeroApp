package com.teamtwo.model.service;
import java.util.List;
import com.teamtwo.dto.entity.Organisation;

public interface OrganisationService {


		 public Organisation searchOrganisationById(int orgId);
		 public List<Organisation> getAllOrganisations();
		 public boolean insertOrganisation(Organisation organistaion);
		 public Organisation deleteOrganisation(int orgId);
		 public Organisation updateOrganisation(Organisation organisation);
	}



