package com.teamtwo.dto.entity;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Organisation {

	private int orgId;
	private String orgName;
	private String orgDesc;
	private String orgAddress;
	
}
