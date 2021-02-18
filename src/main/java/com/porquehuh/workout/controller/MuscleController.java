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
import org.springframework.web.server.ResponseStatusException;

import com.porquehuh.workout.dto.MuscleDTO;
import com.porquehuh.workout.mapper.MuscleMapper;
import com.porquehuh.workout.service.MuscleService;
import com.porquehuh.workout.utils.MyResourceNotFoundException;

import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/muscles")
@Slf4j
public class MuscleController {

	@Autowired
	private MuscleService muscleService;
	
	@Autowired
	private MuscleMapper muscleMapper;
	
	@GetMapping
	ResponseEntity<List<MuscleDTO>> all() {
		
		log.info("Muscle controller");		
		try {
			log.info("Fetching all muscles");	
			return ResponseEntity.ok(muscleMapper.musclesToMusclesDtos(muscleService.findAll()));
		}
		catch (MyResourceNotFoundException exc ) {
			log.info("Failed to fetch all muscles");	
			throw new ResponseStatusException(
					HttpStatus.NOT_FOUND, "all not found", exc);
		}
		
	}
	
	@GetMapping("/{name}")
	public ResponseEntity<MuscleDTO> findByName(@PathVariable String name) {
		
		log.info("Muscle controller");		
		try {
			log.info("Finding muscles by name {}", name);	
			return ResponseEntity.ok(muscleMapper.muscleToMuscleDto(muscleService.findByName(name)));
		}
		catch (MyResourceNotFoundException exc ) {
			log.info("Failed to fetch muscle by name {}", name);	
			throw new ResponseStatusException(
					HttpStatus.NOT_FOUND, "all not found", exc);
		}
		
	}
	
	@PostMapping
	public ResponseEntity<MuscleDTO> create(@RequestBody MuscleDTO muscleDto) {
		
		if(muscleService.isMuscleExists(muscleDto.getId())) {
			log.info("Muscle with id {} already exist", muscleDto.getId());	
			return ResponseEntity.status(HttpStatus.CONFLICT).body(muscleDto);
		}
		
		try {
			log.info("Creating muscle {}", muscleDto.toString());	
			muscleService.save(muscleMapper.muscleDtoToMuscle(muscleDto));
			
			return ResponseEntity.status(HttpStatus.CREATED).body(muscleDto);
		}
		catch (MyResourceNotFoundException exc ) {
			log.info("Failed to fetch muscle by name {}", muscleDto.toString());	
			throw new ResponseStatusException(
					HttpStatus.CONFLICT, "all not found", exc);
		}

	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> delete(@PathVariable Long id) {
		
		log.info("Muscle controller");	
		if (muscleService.isMuscleExists(id)) {
			log.info("Deleting muscle with id {}", id);	
			muscleService.deleteById(id);
			return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
		} else {
			log.info("Failed deleting muscle with id {}", id);	
			return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
		}
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<MuscleDTO> update(@PathVariable Long id, @RequestBody MuscleDTO muscleDto) {
		
		log.info("Muscle controller");	
		if(!muscleService.isMuscleExists(id)) {
			log.info("Muscle with id {} does not exist", id);	
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(muscleDto);
		}
		
		log.info("Updating muscle with id {}", id);	
		muscleDto.setId(id);
		muscleService.save(muscleMapper.muscleDtoToMuscle(muscleDto));
		
		return ResponseEntity.status(HttpStatus.ACCEPTED).body(muscleDto);
		
	}
	
	
}
