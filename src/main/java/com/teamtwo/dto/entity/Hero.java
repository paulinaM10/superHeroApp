package com.teamtwo.dto.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Hero {

	private int heroId;
	private String heroName;
	private String heroDesc;
	private String heroSuperPower;
	 private String imagePath;
}
