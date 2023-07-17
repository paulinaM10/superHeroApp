package com.teamtwo.dto.entity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import javax.validation.constraints.*;


@Data
@AllArgsConstructor
@NoArgsConstructor

public class SuperPower {
	
	
@NotNull(message = "Super power ID is required")
@Min(value = 1, message = "Super power ID must be greater than 0")	
 private int superPowerId;


@NotBlank(message = "Super power name is required")
 private String superPowerName;
 
 
@NotNull(message = "Hero ID is required")
@Min(value = 1, message = "Hero ID must be greater than 0")
 private int heroId;
}
