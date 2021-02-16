package com.porquehuh.workout.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.porquehuh.workout.domain.Muscle;

@Repository
public interface MuscleRepository extends CrudRepository<Muscle, Long>{

	Muscle findByName(String name);
	
}
