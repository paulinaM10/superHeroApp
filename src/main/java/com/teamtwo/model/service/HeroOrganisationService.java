package com.teamtwo.model.service;

import java.util.List;

import com.teamtwo.dto.entity.HeroOrganisation;

public interface HeroOrganisationService {
	
	public List <HeroOrganisation> getAllMembersByOrganisation(int orgId);
	public List <HeroOrganisation> getOrganisationBySuperHero(int heroId);
	

}
