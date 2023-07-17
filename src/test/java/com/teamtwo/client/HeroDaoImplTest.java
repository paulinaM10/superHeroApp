package com.teamtwo.client;


import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.ActiveProfiles;


import com.teamtwo.dto.entity.Hero;
import com.teamtwo.model.persistence.HeroDaoImpl;

import java.util.List;


@SpringBootTest
@ActiveProfiles("test")
public class HeroDaoImplTest {
	
    @Autowired
    private HeroDaoImpl dao;
    
    @Autowired
    private JdbcTemplate jdbcTemplate;
    
    @BeforeEach
    public void setup() {
    	
      
    	jdbcTemplate.update("INSERT INTO HERO(heroId, heroName ,heroDesc, heroSuperPower) VALUES (?, ?, ?, ?)", 7, "TestHeroName", "TestHeroDescription", "TestHeroSuperPower");
        jdbcTemplate.update("INSERT INTO HERO(heroId, heroName ,heroDesc,heroSuperPower) VALUES (?, ?, ?, ?)", 8, "TestHeroName2", "TestHeroDescription2", "TestHeroSuperPower2");
    }
    
    
    @AfterEach
    public void cleanup() {
     
    	 jdbcTemplate.update("DELETE FROM HERO WHERE HEROID IN (?, ?)", 7, 8);
    }

    
    @Test
    public void getHeroById() {
      Hero hero= dao.getHeroById(7);
        assertNotNull(hero);
        assertEquals(7, hero.getHeroId());
    }

  
    
    @Test
    public void testGetAllHeroes() {
        List<Hero> heroes = dao.getAllHeroes();

     
        System.out.println("Actual Count: " + heroes.size());

        for (Hero hero : heroes) {
            System.out.println(hero);
        }

        assertNotNull(heroes);
        assertEquals(6, heroes.size());
    }
    

    @Test
    public void testEditHero() {
        Hero hero = dao.getHeroById(7);
        hero.setHeroName("New Test HeroName");
        hero.setHeroDesc("New Test HeroDescription");
        hero.setHeroSuperPower("New Test HeroPower");

        int rowsAffected = dao.editHero(hero);
        assertEquals(1, rowsAffected);

       Hero updatedHero = dao.getHeroById(7);
        assertEquals("New Test HeroName", updatedHero.getHeroName());
        assertEquals("New Test HeroDescription", updatedHero.getHeroDesc());
        assertEquals("New Test HeroPower", updatedHero.getHeroSuperPower());
    }


    
    @Test
    public void testAddHero() {
       Hero hero = new Hero();
        hero.setHeroName("TestHeroName3");
        hero.setHeroDesc("TestDescription3");
       hero.setHeroSuperPower("TestPower3");

        int rowsAffected = dao.addHero(hero);
        assertEquals(1, rowsAffected);

    
        jdbcTemplate.update("DELETE FROM HERO WHERE heroName = ?", "TestHeroName3");
    }
    

    
    @Test
    public void getHeroByIdInvalidId() {
       Hero hero= dao.getHeroById(29);
        assertNull(hero);
    }


    @Test
    public void testDeleteHeroInvalidId() {
        int rowsAffected = dao.deleteHero(29);
        assertEquals(0, rowsAffected);

    }
}



