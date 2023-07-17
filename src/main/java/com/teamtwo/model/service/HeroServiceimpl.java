package com.teamtwo.model.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.teamtwo.dto.entity.Hero;
import com.teamtwo.model.persistence.HeroDao;

@Service
public class HeroServiceimpl implements HeroService {
	
	@Autowired
	private HeroDao dao;

	@Override
	public List<Hero> getAllHeroes() {
	
		return dao.getAllHeroes();
	}

	@Override
	public boolean addHero(Hero hero) {
	
		return dao.addHero(hero) > 0;
	}

	@Override
	public boolean deleteHeroById(int heroId) {

		return dao.deleteHero(heroId) > 0;
	}

	@Override
	public List<Hero> getHeroByLocation(int locationId) {
	
		return dao.getSuperHeroByLocation(locationId);
	}



	@Override
	public Hero searchHeroById(int heroId) {
		return dao.getHeroById(heroId);
	}

	@Override
	public Hero editHero(int heroId, String heroName, String heroDesc, String heroSuperPower) {
		 Hero hero = dao.getHeroById(heroId);
	        
	        if (hero != null) {
	
	            hero.setHeroName(heroName);
	            hero.setHeroDesc(heroDesc);
	            hero.setHeroSuperPower(heroSuperPower);
	            
	            int numRowsAffected = dao.editHero(hero);

	            if (numRowsAffected > 0) {
	                return hero; 
	            }
	        }

	        return null; 
	            
	         
	}

	@Override
    public void updateImagePath(int heroId, String imagePath) {
        dao.updateImagePath(heroId, imagePath);
    }

	@Override
	public void updateHero(Hero hero) {
		dao.updateHero(hero);		
	}
	
}




