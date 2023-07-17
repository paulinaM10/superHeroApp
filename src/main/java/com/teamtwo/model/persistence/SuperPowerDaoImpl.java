package com.teamtwo.model.persistence;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.teamtwo.dto.entity.SuperPower;

@Repository
public class SuperPowerDaoImpl implements SuperPowerDao {

	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	@Override
	public SuperPower getSuperPowerById(int superPowerId) {
		return jdbcTemplate.queryForObject("SELECT * FROM SuperPower WHERE SuperPowerId=?", new SuperPowerRowMapper(), superPowerId);
	}

	@Override
	public List<SuperPower> getAllSuperPowers() {
		return jdbcTemplate.query("SELECT * FROM SuperPower", new SuperPowerRowMapper());
	}

	@Override
	public int editSuperPower(SuperPower superPower) {
		return jdbcTemplate.update("UPDATE SuperPower SET superPowerName = ?, heroId = ? WHERE superPowerId = ?", superPower.getSuperPowerName(), superPower.getHeroId(), superPower.getSuperPowerId());
	}

	@Override
	public int addSuperPower(SuperPower superPower) {
		return jdbcTemplate.update("INSERT INTO SuperPower VALUES (?, ?, ?) ", superPower.getSuperPowerId(), superPower.getSuperPowerName(), superPower.getHeroId());
	}

	@Override
	public int deleteSuperPower(int superPowerId) {
		return jdbcTemplate.update("DELETE FROM SuperPower WHERE superPowerId=?", superPowerId);
	}

}
