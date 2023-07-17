package com.teamtwo.model.persistence;

import java.util.List;

import com.teamtwo.dto.entity.HeroOrganisation;

public interface HeroOrganisationDao {

	List <HeroOrganisation> getAllMembersByOrganisation(int orgId);
	List <HeroOrganisation> getOrganisationBySuperHero(int heroId);
}
