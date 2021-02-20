package com.porquehuh.workout.controller;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.server.ResponseStatusException;

import com.porquehuh.workout.domain.Equipment;
import com.porquehuh.workout.domain.Excercise;
import com.porquehuh.workout.domain.Muscle;
import com.porquehuh.workout.dto.ExcerciseDTO;
import com.porquehuh.workout.mapper.ExcerciseMapper;
import com.porquehuh.workout.service.EquipmentService;
import com.porquehuh.workout.service.ExcerciseService;
import com.porquehuh.workout.service.MuscleService;
import com.porquehuh.workout.utils.MyResourceNotFoundException;

import lombok.extern.slf4j.Slf4j;

@Controller
@RequestMapping("/excercise")
@Slf4j
public class ExcerciseController {
	
	@Autowired
	private ExcerciseService excersiceService;
	
	@Autowired
	private EquipmentService equipmentService;
	
	@Autowired
	private MuscleService muscleService;
	
	@Autowired
	ExcerciseMapper excerciseMapper;
	
	
	@GetMapping
	ResponseEntity<List<ExcerciseDTO>> all() {
		
		log.info("Excercise controller");
		try {
			log.info("Fetching all excercises");	
			return ResponseEntity.ok(excerciseMapper.excercisesToExcercisesDtos(excersiceService.findAll()));
		}
		catch (MyResourceNotFoundException exc ) {
			log.info("Failed to fetch all excercises");	
			throw new ResponseStatusException(
					HttpStatus.NOT_FOUND, "all not found", exc);
		}
		
	}
	
	@GetMapping("/muscles")
	ResponseEntity<Map<String,List<ExcerciseDTO>>> byMuscle() {
		
		log.info("Excercise controller");
		try {
			log.info("Fetching all excercises by muscle");	
			List<ExcerciseDTO> excercises =  excerciseMapper.excercisesToExcercisesDtos(excersiceService.findAll());
			
			Map<String , List<ExcerciseDTO>> groupByMuscle = excercises.stream().collect(Collectors.groupingBy(ExcerciseDTO::getMuscle));
			
			return ResponseEntity.ok(groupByMuscle);
		}
		catch (MyResourceNotFoundException exc ) {
			log.info("Failed to fetch all excercises by muscle");	
			throw new ResponseStatusException(
					HttpStatus.NOT_FOUND, "all not found", exc);
		}
		
	}
	
	@GetMapping("/equipment")
	ResponseEntity<Map<String,List<ExcerciseDTO>>> byEquipment() {
		
		log.info("Excercise controller");
		try {
			log.info("Fetching all excercises by equipment");	
			List<ExcerciseDTO> excercises =  excerciseMapper.excercisesToExcercisesDtos(excersiceService.findAll());
			
			Map<String,List<ExcerciseDTO>> groupByEquipment = excercises.stream().collect(Collectors.groupingBy(ExcerciseDTO::getEquipment));
			
			return ResponseEntity.ok(groupByEquipment);
		}
		catch (MyResourceNotFoundException exc ) {
			log.info("Failed to fetch all excercises by equipment");
			throw new ResponseStatusException(
					HttpStatus.NOT_FOUND, "all not found", exc);
		}

	}
	
	@GetMapping("/{name}")
	public ResponseEntity<ExcerciseDTO> findByName(@PathVariable String name) {
		
		log.info("Excercise controller");
		try {
			log.info("Fetching all excercises by equipment");	
			return ResponseEntity.status(HttpStatus.FOUND).body(excerciseMapper.excerciseToExcerciseDto(excersiceService.findByName(name)));
		}
		catch (MyResourceNotFoundException exc ) {
			log.info("Failed to fetch all excercises by equipment");
			throw new ResponseStatusException(
					HttpStatus.NOT_FOUND, "all not found", exc);
		}
		
	}
	
	@PostMapping
	public ResponseEntity<ExcerciseDTO> create(@RequestBody ExcerciseDTO excerciseDTO) {
		
		Equipment equipment = equipmentService.findByName(excerciseDTO.getEquipment());
		Muscle muscle = muscleService.findByName(excerciseDTO.getMuscle());
		
		if(excersiceService.isExcerciseExist(excerciseDTO.getId())) {
			log.info("Excercise with id {} already exists", excerciseDTO.getId());
			return ResponseEntity.status(HttpStatus.CONFLICT).body(excerciseDTO);
		}
		
		if(null == equipment || null == muscle) {
			log.info("Either equipment or muscle doenst exist");
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(excerciseDTO);
		}
		
		try {
			log.info("Creating excercise {}", excerciseDTO);
			
			Excercise  excercise = Excercise.builder().name(excerciseDTO.getName()).description(excerciseDTO.getDescription())
					.equipment(equipment).muscle(muscle).build();
			
			excersiceService.save(excercise);
			return ResponseEntity.status(HttpStatus.CREATED).body(excerciseDTO);
		}
		catch (MyResourceNotFoundException exc ) {
			log.info("Failed to create excercise {}", excerciseDTO);
			throw new ResponseStatusException(
					HttpStatus.NOT_FOUND, "Not created", exc);
		}
		
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<ExcerciseDTO> update(@PathVariable Long id, @RequestBody ExcerciseDTO excerciseDTO) {
		
		if(!excersiceService.isExcerciseExist(id)) {
			log.info("Excercise with id {} does not exist", id);
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(excerciseDTO);
		}
		
		Equipment equipment = equipmentService.findByName(excerciseDTO.getEquipment());
		Muscle muscle = muscleService.findByName(excerciseDTO.getMuscle());
		
		if(null == equipment || null == muscle) {
			log.info("Either equipment or muscle doenst exist");
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(excerciseDTO);
		}
		
		try {
			log.info("Updating excercise {}", excerciseDTO);
			
			Excercise  excercise = Excercise.builder().id(id).name(excerciseDTO.getName()).description(excerciseDTO.getDescription())
					.equipment(equipment).muscle(muscle).build();
			
			excersiceService.save(excercise);
			return ResponseEntity.status(HttpStatus.CREATED).body(excerciseDTO);
		}
		catch (MyResourceNotFoundException exc ) {
			log.info("Failed to create excercise {}", excerciseDTO);
			throw new ResponseStatusException(
					HttpStatus.NOT_FOUND, "Not updated", exc);
		}
		
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> delete(@PathVariable Long id) {
		
		if(excersiceService.isExcerciseExist(id)) {
			log.info("deleting excercise");
			excersiceService.deleteById(id);
			return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
		} else {
			log.info("excercise does not exist");
			return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
		}
		
	}

}
