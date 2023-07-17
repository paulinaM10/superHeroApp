package com.teamtwo.model.persistence;

import java.util.List;

import com.teamtwo.dto.entity.SuperPower;


public interface SuperPowerDao {

	public SuperPower getSuperPowerById(int superPowerId);
	public List<SuperPower> getAllSuperPowers();
	public int editSuperPower(SuperPower superPower);
	public int addSuperPower(SuperPower superPower);
	public int deleteSuperPower(int superPowerId);
}
