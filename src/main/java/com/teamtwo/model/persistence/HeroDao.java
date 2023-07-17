package com.teamtwo.model.persistence;

import java.util.List;

import com.teamtwo.dto.entity.Hero;

public interface HeroDao {

	public Hero getHeroById(int heroId);
	public List<Hero> getAllHeroes();
	public int editHero(Hero hero);
	public int addHero(Hero hero);
	public int deleteHero(int heroId);
	List <Hero> getSuperHeroByLocation(int locationId);
	void updateImagePath(int heroId, String imagePath);
	public void updateHero(Hero hero);
}
