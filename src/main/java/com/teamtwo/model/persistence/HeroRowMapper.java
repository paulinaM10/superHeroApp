package com.teamtwo.model.persistence;

import java.sql.ResultSet;


import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.teamtwo.dto.entity.Hero;
public class HeroRowMapper implements RowMapper<Hero> {

	@Override
	public Hero mapRow(ResultSet resultset, int rowNum) throws SQLException {
	    Hero hero = new Hero();

	    hero.setHeroId(resultset.getInt("heroId"));
	    hero.setHeroName(resultset.getString("heroName"));
	    hero.setHeroDesc(resultset.getString("heroDesc"));
	    hero.setHeroSuperPower(resultset.getString("heroSuperPower"));
	    hero.setImagePath(resultset.getString("imagePath"));

	    return hero;
	}

}
