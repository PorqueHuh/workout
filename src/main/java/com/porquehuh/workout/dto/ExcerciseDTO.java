package com.porquehuh.workout.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter @NoArgsConstructor
public class ExcerciseDTO {
	
	private String name;
	private String description;
	private String equipment;
	private String muscle;

}
