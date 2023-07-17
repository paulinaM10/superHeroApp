package com.teamtwo.model.service;

import java.util.List;

import com.teamtwo.dto.entity.SuperPower;

public interface SuperPowerService {
	
	
	public SuperPower getSuperPowerById(int superPowerId);
	public List<SuperPower> getAllSuperPowers();
	public boolean editSuperPower(SuperPower superPower);
	public boolean addSuperPower(SuperPower superPower);
	public boolean deleteSuperPower(int superPowerId);

}
