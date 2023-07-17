package com.teamtwo.dao;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;

import com.teamtwo.dto.entity.Organisation;
import com.teamtwo.model.persistence.OrganisationDaoImpl;
import java.util.List;


import com.teamtwo.*;

@SpringBootTest
@ActiveProfiles("test")
@ContextConfiguration(classes={com.teamtwo.client.SuperHeroApplication.class})

public class OrganisationDaoImplTest {

    @Autowired
    private OrganisationDaoImpl dao;
    
    @Autowired
    private JdbcTemplate jdbcTemplate;

    @BeforeEach
    public void setup() {
       
        jdbcTemplate.update("INSERT INTO Organisation (orgId, orgName, orgDesc, orgAddress) VALUES (?, ?, ?, ?)", 1, "Test Org", "Test Description", "Test Address");
        jdbcTemplate.update("INSERT INTO Organisation (orgId, orgName, orgDesc, orgAddress) VALUES (?, ?, ?, ?)", 2, "Test Org 2", "Test Description 2", "Test Address 2");
    }

    @AfterEach
    public void cleanup() {
        // Clean up after test
        jdbcTemplate.update("DELETE FROM Organisation WHERE orgId IN (?, ?)", 1, 2);
    }

    @Test
    public void getOrganisationById_GoldenPath() {
        Organisation org = dao.getOrganisationById(1);
        assertNotNull(org);
        assertEquals(1, org.getOrgId());
    }

    @Test
    public void getAllOrganisations_GoldenPath() {
        List<Organisation> orgs = dao.getAllOrganisations();
        assertEquals(5, orgs.size());
    }

    @Test
    public void addOrganisation_GoldenPath() {
        Organisation org = new Organisation();
        org.setOrgName("Test Org 3");
        org.setOrgDesc("Test Description 3");
        org.setOrgAddress("Test Address 3");

        int rowsAffected = dao.addOrganisation(org);
        assertEquals(1, rowsAffected);

        // Cleanup the newly added organisation
        jdbcTemplate.update("DELETE FROM Organisation WHERE orgName = ?", "Test Org 3");
    }

    @Test
    public void editOrganisation_GoldenPath() {
        Organisation org = dao.getOrganisationById(1);
        org.setOrgName("New Test Org");
        org.setOrgDesc("New Test Description");
        org.setOrgAddress("New Test Address");

        int rowsAffected = dao.editOrganisation(org);
        assertEquals(1, rowsAffected);

        Organisation updatedOrg = dao.getOrganisationById(1);
        assertEquals("New Test Org", updatedOrg.getOrgName());
        assertEquals("New Test Description", updatedOrg.getOrgDesc());
        assertEquals("New Test Address", updatedOrg.getOrgAddress());
    }

    @Test
    public void deleteOrganisation_GoldenPath() {
        int rowsAffected = dao.deleteOrganisation(2);
        assertEquals(1, rowsAffected);

        Organisation deletedOrg = dao.getOrganisationById(2);
        assertNull(deletedOrg);
    }

    @Test
    public void getOrganisationById_InvalidId() {
        Organisation org = dao.getOrganisationById(999);
        assertNull(org);
    }

    @Test
    public void deleteOrganisation_InvalidId() {
        int rowsAffected = dao.deleteOrganisation(999);
        assertEquals(0, rowsAffected);
    }
}
