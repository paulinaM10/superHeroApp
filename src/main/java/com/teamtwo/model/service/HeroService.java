package com.teamtwo.model.service;

import java.util.List;


import com.teamtwo.dto.entity.Hero;


public interface HeroService {
	
	List<Hero> getAllHeroes();
	boolean addHero(Hero hero);
	boolean deleteHeroById(int heroId);
	List <Hero> getHeroByLocation(int locationId);

	public Hero editHero(int heroId, String heroName, String heroDesc, String heroSuperPower);
	public Hero searchHeroById (int heroId);
    void updateImagePath(int heroId, String imagePath);
	void updateHero(Hero hero);



}
