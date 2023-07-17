package com.teamtwo.model.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.teamtwo.dto.entity.HeroOrganisation;
import com.teamtwo.model.persistence.HeroOrganisationDao;


@Service
public class HeroOrganisationServiceImpl implements HeroOrganisationService {

	@Autowired 
	HeroOrganisationDao heroOrganisationDao;
	
	
	@Override
	public List<HeroOrganisation> getAllMembersByOrganisation(int orgId) {
		return heroOrganisationDao.getAllMembersByOrganisation(orgId);
	}

	@Override
	public List<HeroOrganisation> getOrganisationBySuperHero(int heroId) {
	    return heroOrganisationDao.getOrganisationBySuperHero(heroId);
	}


}
