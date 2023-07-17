package com.teamtwo.model.persistence;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.teamtwo.dto.entity.Hero;

@Repository
public class HeroDaoImpl implements HeroDao {
	
	@Autowired
	private JdbcTemplate jdbcTemplate;
	


	@Override
	public Hero getHeroById(int heroId) {
		
		String query = "SELECT * FROM HERO WHERE HEROID=?";
		 try {
		 
		 return jdbcTemplate.queryForObject(query,new HeroRowMapper(), heroId);
		 
		       }catch (EmptyResultDataAccessException e) {
			 return null;
		 }
	}

	@Override
	public List<Hero> getAllHeroes() {
		String query = "SELECT * FROM HERO";
		return jdbcTemplate.query(query, new HeroRowMapper());
	}

	@Override
	public int editHero(Hero hero) {
		String query = "UPDATE HERO SET heroName = ?, heroDesc = ?, heroSuperPower = ? WHERE heroId = ?";
        return jdbcTemplate.update(query, hero.getHeroName(), hero.getHeroDesc(), hero.getHeroSuperPower(), hero.getHeroId());
	}

	@Override
	public int addHero(Hero hero) {
		String query = "INSERT INTO HERO VALUES(?,?,?,?)";
		return jdbcTemplate.update(query, hero.getHeroId(), hero.getHeroName(), hero.getHeroDesc(),
				hero.getHeroSuperPower());
	}

	@Override
	public int deleteHero(int heroId) {
		String query = "DELETE FROM HERO WHERE heroId =?";
		return jdbcTemplate.update(query, heroId);
	}

	@Override
	public List<Hero> getSuperHeroByLocation(int locationId) {
		String query = "SELECT H.* FROM HERO H JOIN Sighting S ON H.heroId = S.heroId WHERE S.locationId = ?";
        return jdbcTemplate.query(query, new HeroRowMapper());
	}

	@Override
    public void updateImagePath(int heroId, String imagePath) {
        String sql = "UPDATE Hero SET imagePath = ? WHERE heroId = ?";
        jdbcTemplate.update(sql, imagePath, heroId);
    }

    @Override
    public void updateHero(Hero hero) {
        jdbcTemplate.update("UPDATE Hero SET imagePath = ? WHERE heroId = ?", 
                             hero.getImagePath(), hero.getHeroId());
    }   
	}



