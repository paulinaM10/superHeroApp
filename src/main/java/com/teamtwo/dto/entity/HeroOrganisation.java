package com.teamtwo.dto.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class HeroOrganisation {

	private int serialId;
	private int heroId;
	private int orgId;
}
