package com.teamtwo.client;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.List;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.ActiveProfiles;

import com.teamtwo.dto.entity.SuperPower;
import com.teamtwo.model.persistence.SuperPowerDaoImpl;



@SpringBootTest
@ActiveProfiles("test")
public class SuperPowerDaoImplTest {
	
	@Autowired
	private SuperPowerDaoImpl superPowerDao;
	
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	 @BeforeEach
	    public void setup() {
	       
	        jdbcTemplate.update("INSERT INTO SuperPower (superPowerId, superPowerName, heroId) VALUES (?, ?, ?)", 06, "Great strength", 1);
	        jdbcTemplate.update("INSERT INTO SuperPower (superPowerId, superPowerName, heroId) VALUES (?, ?, ?)", 07, "Tough Armour", 2);
	    }
	 
	   @AfterEach
	    public void cleanup() {
	        jdbcTemplate.update("DELETE FROM SuperPower WHERE superPowerId IN (?, ?, ?)", 06, 07, 8);
	    }


	
	@Test
	public void testGetSuperPowerById() {
		SuperPower findPower = superPowerDao.getSuperPowerById(06);
		assertEquals(06, findPower.getSuperPowerId());
	}

	
	@Test
	public void testAddSuperPower() {
		SuperPower power= new SuperPower();
		power.setSuperPowerId(8);
		power.setSuperPowerName("Super Strength");
		power.setHeroId(2);
		
		int rows = superPowerDao.addSuperPower(power);
		assertEquals(1, rows);
		
		jdbcTemplate.update("DELETE FROM SuperPower WHERE superPowerId=?", 8);

	
}
	@Test
	public void testEditSuperPower() {
		SuperPower originPower = superPowerDao.getSuperPowerById(06);
	    assertNotNull(originPower);
	    
	    originPower.setSuperPowerName("Immense Strength");
	    originPower.setHeroId(2);
	    
	    int rows = superPowerDao.editSuperPower(originPower);
	    assertEquals(1, rows);
	}
	
	@Test
	public void testDeleteSuperMan() {
		assertEquals(1, superPowerDao.deleteSuperPower(07));
	}
	@Test 
	public void testGetAllSuperPower() {
		List<SuperPower> power = superPowerDao.getAllSuperPowers();
		assertEquals(4, power.size());
		
	}

}