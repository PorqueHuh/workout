package com.porquehuh.workout.domain;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
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

	 @OneToMany(cascade = CascadeType.ALL, mappedBy = "muscle", fetch = FetchType.EAGER)
	 @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
	 private List<Excercise> exercise;

}
