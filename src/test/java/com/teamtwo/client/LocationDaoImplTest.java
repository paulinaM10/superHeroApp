package com.teamtwo.client;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.ActiveProfiles;


import com.teamtwo.dto.entity.Location;
import com.teamtwo.model.persistence.LocationDaoImpl;

@SpringBootTest
@ActiveProfiles("test")

public class LocationDaoImplTest {

	 @Autowired
	 private LocationDaoImpl dao;
	 
	 @Autowired
	 private JdbcTemplate jdbcTemplate;
 
	 @Test
		public void testGetLocationById() {
		    Location retrievedLocation = dao.getLocationById(001);
		    assertEquals(001, retrievedLocation.getLocationId());
		}
		
	 @Test
		public void testGetAllLocations_GoldenPath() {
			List<Location> location = dao.getAllLocations();
			assertEquals(3, location.size());
	 }
	 
	 @Test
	 public void testAddLocation_GoldenPath() {
	        Location locations = new Location();
	        locations.setLocationId(999);
	        locations.setLocationName("Test 1");
	        locations.setLocationDesc("Test 2");
	        locations.setLocationAddress("Test 3");
	        locations.setCoordinates("Test 4");

	        int rowsAffected = dao.addLocation(locations);
	        assertEquals(1, rowsAffected);

	   
	        jdbcTemplate.update("DELETE FROM Location WHERE locationId = ?", 999);
	    }
	 
	  @Test
	    public void editLocation_GoldenPath() {
	        Location location = dao.getLocationById(1);
	        location.setLocationName("Test 1");
	        location.setLocationDesc("Test 2");
	        location.setLocationAddress("Test 3");
	        location.setCoordinates("Test 4");

	        int rowsAffected = dao.editLocation(location);
	        assertEquals(1, rowsAffected);

	        Location updatedLocation = dao.getLocationById(1);
	        assertEquals("Test 1", updatedLocation.getLocationName());
	        assertEquals("Test 2", updatedLocation.getLocationDesc());
	        assertEquals("Test 3", updatedLocation.getLocationAddress());
	        assertEquals("Test 4", updatedLocation.getCoordinates());
	    }
	  
	    @Test
	    public void deleteLocation_InvalidId() {
	        int rowsAffected = dao.deleteLocation(999);
	        assertEquals(0, rowsAffected);
	    }
	    
	  

}