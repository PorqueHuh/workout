package com.porquehuh.workout.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter @Setter @NoArgsConstructor
@JsonIdentityInfo(
		  generator = ObjectIdGenerators.PropertyGenerator.class, 
		  property = "id")
public class Excercise {
	
	@Id
	@GeneratedValue
	@Column(name="excercise_id")
	private long id;
	
	private String name;
	
	@Column(length=400)
	private String description;
	
//	@ManyToOne(fetch=FetchType.LAZY)
//	@JoinColumn(name="equipment_id")
//	@JsonBackReference
//	@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
//	private Equipment equipment;
//	
//	@ManyToOne(fetch=FetchType.LAZY)
//	@JoinColumn(name="muscle_id")
//	@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
//	private Muscle muscle;

}
