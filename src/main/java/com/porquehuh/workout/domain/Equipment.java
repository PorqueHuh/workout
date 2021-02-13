package com.porquehuh.workout.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "equipment")
@Getter @Setter @NoArgsConstructor
public class Equipment {
	
	@Id
	@GeneratedValue
	private Long id;
	
	private String name;

}
