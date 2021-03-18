package com.porquehuh.workout.actuator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.actuate.info.Info.Builder;
import org.springframework.boot.actuate.info.InfoContributor;
import org.springframework.stereotype.Component;

import com.porquehuh.workout.repository.ExcerciseRepository;

@Component
public class ExcercisesInfoContributor implements InfoContributor {
	
	@Autowired
	ExcerciseRepository excerciseRepository;

	@Override
	public void contribute(Builder builder) {
		
		Long numExcercises = excerciseRepository.count();
		
		builder.withDetail("Excercices",numExcercises);
		
	}

}
