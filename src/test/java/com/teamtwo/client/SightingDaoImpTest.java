package com.teamtwo.client;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.ActiveProfiles;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.sql.Date;
import java.util.List;

import com.teamtwo.dto.entity.Sighting;
import com.teamtwo.model.persistence.SightingDaoImpl;

@SpringBootTest
@ActiveProfiles("test")
public class SightingDaoImpTest {
	
	@Autowired
	private SightingDaoImpl sightingDao;
	
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	
	 @BeforeEach
	    public void setup() {
	       
	        jdbcTemplate.update("INSERT INTO Sighting (sightingId, heroId, locationId, locationDate) VALUES (?, ?, ?, ?)" ,888, 3, 2,Date.valueOf("2023-01-01"));
	        jdbcTemplate.update("INSERT INTO Sighting (sightingId, heroId, locationId, locationDate) VALUES (?, ?, ?, ?)" ,666, 3, 1, Date.valueOf("2023-01-01"));
	    }
	 
	   @AfterEach
	    public void cleanup() {
	        jdbcTemplate.update("DELETE FROM Sighting WHERE sightingId IN (?, ?, ?)", 888, 666, 777);
	    }

	   
	@Test
	public void testGetSightingById() {
	    Sighting retrievedSighting = sightingDao.getSightingById(888);
	    assertEquals(888, retrievedSighting.getSightingId());
	}
	
	
	@Test
	public void testEditSighting() {
	    Sighting originalSighting = sightingDao.getSightingById(888);
	    assertNotNull(originalSighting);

	    originalSighting.setHeroId(2);
	    originalSighting.setLocationId(002);

	    int rowsAffected = sightingDao.editSighting(originalSighting);
	    assertEquals(1, rowsAffected);

	    Sighting updatedSighting = sightingDao.getSightingById(888);
	    assertNotNull(updatedSighting);

	    assertEquals(2, updatedSighting.getHeroId());
	    assertEquals(002, updatedSighting.getLocationId());
	}

		
		

	@Test
	public void testAddSighting() {
		Sighting sight = new Sighting();
		sight.setSightingId(777);
		sight.setHeroId(3);
		sight.setLocationId(003);
		sight.setLocationDate(Date.valueOf("2023-01-01"));
		
		int rows = sightingDao.addSighting(sight);
		assertEquals(1, rows);
		
		jdbcTemplate.update("DELETE FROM Sighting WHERE sightingId = ?", 777);
		
	}

	
	@Test
	public void testDeleteSighting() {
		assertEquals(1, sightingDao.deleteSighting(666));
		
	}

	  
	@Test
	public void testGetSightingByDate() {
	    Date date = Date.valueOf("2023-01-01");

	    List<Sighting> retrievedSightings = sightingDao.getSightingByDate(date);

	    Assertions.assertEquals(3, retrievedSightings.size());

	    Assertions.assertEquals(111, retrievedSightings.get(0).getSightingId());
	}
	@Test
	public void testGetAllSightings() {
		List<Sighting> sightings = sightingDao.getAllSighting();
		assertEquals(3, sightings.size());
	}
	
    }

				
 