package com.porquehuh.workout.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.porquehuh.workout.domain.Muscle;
import com.porquehuh.workout.dto.MuscleDTO;
import com.porquehuh.workout.mapper.MuscleMapper;
import com.porquehuh.workout.service.MuscleService;

@RestController
@RequestMapping("/muscles")
public class MuscleController {

	@Autowired
	private MuscleService muscleService;
	
	@Autowired
	private MuscleMapper muscleMapper;
	
	@GetMapping
	ResponseEntity<List<MuscleDTO>> all() {
		
		return ResponseEntity.ok(muscleMapper.musclesToMusclesDtos(muscleService.findAll()));
	}
	
	@GetMapping("/{name}")
	public ResponseEntity<MuscleDTO> findByName(@PathVariable String name) {
		
		return ResponseEntity.ok(muscleMapper.muscleToMuscleDto(muscleService.findByName(name)));
	}
	
	@PostMapping
	public ResponseEntity<Muscle> create(@RequestBody Muscle muscle) {
		
		muscleService.save(muscle);
		
		return ResponseEntity.status(HttpStatus.CREATED).body(muscle);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> delete(@PathVariable Long id) {
		
		if (muscleService.isMuscleExists(id)) {
			muscleService.deleteById(id);
			return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
		} else {
			return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).build();
		}
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<Muscle> update(@PathVariable Long id, @RequestBody Muscle muscle) {
		
		if(!muscleService.isMuscleExists(id)) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(muscle);
		}
		
		muscle.setId(id);
		muscleService.save(muscle);
		
		return ResponseEntity.status(HttpStatus.ACCEPTED).body(muscle);
		
	}
	
	
}
