package com.porquehuh.workout.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.porquehuh.workout.domain.Muscle;
import com.porquehuh.workout.service.MuscleService;

@RestController
public class MuscleController {

	@Autowired
	private MuscleService muscleService;
	
	@GetMapping("/muscles")
	List<Muscle> all() {
		return muscleService.findAll();
	}
}
