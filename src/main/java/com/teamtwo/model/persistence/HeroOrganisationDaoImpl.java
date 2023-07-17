package com.teamtwo.model.persistence;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.teamtwo.dto.entity.HeroOrganisation;


@Repository
public class HeroOrganisationDaoImpl implements HeroOrganisationDao {
	
	@Autowired
	private JdbcTemplate jdbcTemplate;

	@Override
	public List<HeroOrganisation> getAllMembersByOrganisation(int orgId) {
		String query = "SELECT * FROM HeroOrganisation WHERE orgId = ?";
		return jdbcTemplate.query(query, new HeroOrganisationRowMapper(), orgId);
	}

	@Override
	public List<HeroOrganisation> getOrganisationBySuperHero(int heroId) {
		String query = "SELECT * FROM HeroOrganisation WHERE heroId = ?";
		return jdbcTemplate.query(query, new HeroOrganisationRowMapper(), heroId);
	}

}

