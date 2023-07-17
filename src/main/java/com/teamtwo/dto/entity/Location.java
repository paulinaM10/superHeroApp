package com.teamtwo.dto.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Location {
	
	private int locationId;
	private String locationName;
	private String locationDesc;
	private String locationAddress;
	private String coordinates;
	
}
