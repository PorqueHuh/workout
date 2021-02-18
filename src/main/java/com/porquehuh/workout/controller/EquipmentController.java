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

import com.porquehuh.workout.dto.EquipmentDTO;
import com.porquehuh.workout.mapper.EquipmentMapper;
import com.porquehuh.workout.service.EquipmentService;
import com.porquehuh.workout.utils.MyResourceNotFoundException;

import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/equipment")
@Slf4j
public class EquipmentController {
	
	@Autowired
	private EquipmentService equipmentService;
	
	@Autowired
	private EquipmentMapper equipmentMapper;
	
	@GetMapping
	ResponseEntity<List<EquipmentDTO>> all() {
		
		log.info("Equipment controller");		
		try {
			log.info("Fetching all equipment");	
			return ResponseEntity.ok(equipmentMapper.equipmentToEquipmentsDtos(equipmentService.findAll()));
		}
		catch (MyResourceNotFoundException exc ) {
			log.info("Failed to fetch all equipment");	
			throw new ResponseStatusException(
					HttpStatus.NOT_FOUND, "all not found", exc);
		}
	}
	
	@GetMapping("/{name}")
	public ResponseEntity<EquipmentDTO> findByName(@PathVariable String name) {
		
		log.info("Equipment controller");	
		try {
			log.info("Finding equipment by name {}", name);	
			return ResponseEntity.ok(equipmentMapper.equipmentToEquipmentDto(equipmentService.findByName(name)));
		}
		catch (MyResourceNotFoundException exc ) {
			log.error("Failed to fetch equipment by name {}", name);
			throw new ResponseStatusException(
					HttpStatus.NOT_FOUND, name+" not found", exc);
		}
	}
	
	@PostMapping
	public ResponseEntity<EquipmentDTO> create(@RequestBody EquipmentDTO equipmentDto) {
		
		log.info("Equipment controller");
		
		if (equipmentService.isEquipmentExists(equipmentDto.getId())) {
			log.info("Equipment with id {} already exist", equipmentDto.getId());	
			return ResponseEntity.status(HttpStatus.CONFLICT).body(equipmentDto);
		}
		
		try {
			log.info("Creating equipment {}", equipmentDto.toString());	
			equipmentService.save(equipmentMapper.equipmentDtoToEquipment(equipmentDto));
			return ResponseEntity.status(HttpStatus.CREATED).body(equipmentDto);
		}
		catch (MyResourceNotFoundException exc ) {
			log.error("Failed to create equipment {}", equipmentDto.toString());
			throw new ResponseStatusException(
					HttpStatus.CONFLICT, equipmentDto+" not created", exc);
		}
		
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> delete(@PathVariable Long id) {
		
		log.info("Equipment controller");	
		if (equipmentService.isEquipmentExists(id)) {
			log.info("Equipment with id {} exists, deleting", id);	
			equipmentService.deleteById(id);
			return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
		} else {
			log.info("Failed to delete equipment with id {}", id);	
			return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
		}
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<EquipmentDTO> update(@PathVariable Long id, @RequestBody EquipmentDTO equipmentDto) {
			
		if (!equipmentService.isEquipmentExists(id)) {
			log.info("Equipment with id  {} does not exist", id);	
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(equipmentDto);
		}
		
		log.info("Equipment with id {} updating", id);	
		equipmentDto.setId(id);
		equipmentService.save(equipmentMapper.equipmentDtoToEquipment(equipmentDto));
		
		return ResponseEntity.status(HttpStatus.ACCEPTED).body(equipmentDto);
		
	}
	

}
