package com.teamtwo.dto.entity;


import java.sql.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Sighting {

	private int sightingId;
	private int heroId;
	private int locationId;
	private Date locationDate;
	public void setHero(Hero hero) {
		
	}
}
