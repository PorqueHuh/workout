package com.porquehuh.workout.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Getter
@Setter
@NoArgsConstructor
@ToString
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
public class Muscle {

	@Id
	@GeneratedValue
	private Long id;

	private String name;

	// @OneToMany(mappedBy = "muscle")
	// @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
	// private Set<Excercise> exercise;

}
