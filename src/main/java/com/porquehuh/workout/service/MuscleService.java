package com.porquehuh.workout.service;

import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.porquehuh.workout.domain.Muscle;
import com.porquehuh.workout.repository.MuscleRepository;

@Service
public class MuscleService {
	
	@Autowired
	private MuscleRepository muscleRepository;
	
	public List<Muscle> findAll() {
		List<Muscle> muscles = new ArrayList<>();
		muscleRepository.findAll().forEach(muscles::add);
		return muscles;
	}
	
	
	public Muscle findByName(String name) {
		Muscle muscle = muscleRepository.findByName(name);
		return muscle;
	}

}
