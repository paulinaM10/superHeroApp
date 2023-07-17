package com.teamtwo.client;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.ActiveProfiles;
import org.junit.jupiter.api.Assertions;

import com.teamtwo.dto.entity.HeroOrganisation;
import com.teamtwo.model.persistence.HeroOrganisationDaoImpl;

@SpringBootTest
@ActiveProfiles("test")
public class HeroOrganisationDaoImplTest {
	
	
	 @Autowired
	    private HeroOrganisationDaoImpl dao;
	    
	    @Autowired
	    private JdbcTemplate jdbcTemplate;
	    
	    @BeforeEach
	    public void setUp() {
	        // Clear the HeroOrganisation table before each test
	        jdbcTemplate.update("DELETE FROM HeroOrganisation");
	    }

	    @Test
	    public void testGetAllMembersByOrganisation() {
	        // Insert test data into the HeroOrganisation table
	        jdbcTemplate.update("INSERT INTO HeroOrganisation (serialId, heroId, orgId) VALUES (1, 1, 101)");
	        jdbcTemplate.update("INSERT INTO HeroOrganisation (serialId, heroId, orgId) VALUES (2, 2, 101)");

	        // Retrieve the members by organisation
	        List<HeroOrganisation> members = dao.getAllMembersByOrganisation(101);

	        // Assert the size of the returned list
	        Assertions.assertEquals(2, members.size());

	        // Assert the values of the first member
	        HeroOrganisation member1 = members.get(0);
	        Assertions.assertEquals(1, member1.getSerialId());
	        Assertions.assertEquals(1, member1.getHeroId());
	        Assertions.assertEquals(101, member1.getOrgId());

	        // Assert the values of the second member
	        HeroOrganisation member2 = members.get(1);
	        Assertions.assertEquals(2, member2.getSerialId());
	        Assertions.assertEquals(2, member2.getHeroId());
	        Assertions.assertEquals(101, member2.getOrgId());
	    }

	    @Test
	    public void testGetOrganisationBySuperHero() {
	        // Insert test data into the HeroOrganisation table
	        jdbcTemplate.update("INSERT INTO HeroOrganisation (serialId, heroId, orgId) VALUES (1, 1, 101)");
	        jdbcTemplate.update("INSERT INTO HeroOrganisation (serialId, heroId, orgId) VALUES (2, 1, 102)");
	        jdbcTemplate.update("INSERT INTO HeroOrganisation (serialId, heroId, orgId) VALUES (3, 2, 102)");

	        // Retrieve the organisations by super hero
	        List<HeroOrganisation> organisations = dao.getOrganisationBySuperHero(1);

	        // Assert the size of the returned list
	        Assertions.assertEquals(2, organisations.size());

	        // Assert the values of the first organisation
	        HeroOrganisation organisation1 = organisations.get(0);
	        Assertions.assertEquals(1, organisation1.getSerialId());
	        Assertions.assertEquals(1, organisation1.getHeroId());
	        Assertions.assertEquals(101, organisation1.getOrgId());

	        // Assert the values of the second organisation
	        HeroOrganisation organisation2 = organisations.get(1);
	        Assertions.assertEquals(2, organisation2.getSerialId());
	        Assertions.assertEquals(1, organisation2.getHeroId());
	        Assertions.assertEquals(102, organisation2.getOrgId());
	    }
	}


