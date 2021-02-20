package com.porquehuh.workout.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.porquehuh.workout.domain.Excercise;

@Repository
public interface ExcerciseRepository extends CrudRepository<Excercise , Long>{
	
	Excercise findByName(String name);

}
