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

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Getter @Setter @NoArgsConstructor @AllArgsConstructor
@ToString
public class Equipment {

	@Id
	@GeneratedValue
	private Long id;
	
	private String name;
	
	public Equipment(Long id, String name) {
		super();
		this.id = id;
		this.name = name;
	}
	
	@OneToMany(mappedBy = "equipment" , targetEntity = Excercise.class, cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
	private List<Excercise> exercise;
			

}
