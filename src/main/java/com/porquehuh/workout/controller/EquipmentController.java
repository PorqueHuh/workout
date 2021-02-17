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

import com.porquehuh.workout.domain.Equipment;
import com.porquehuh.workout.dto.EquipmentDTO;
import com.porquehuh.workout.mapper.EquipmentMapper;
import com.porquehuh.workout.service.EquipmentService;

@RestController
@RequestMapping("/equipment")
public class EquipmentController {
	
	@Autowired
	private EquipmentService equipmentService;
	
	@Autowired
	private EquipmentMapper equipmentMapper;
	
	@GetMapping
	ResponseEntity<List<EquipmentDTO>> all() {
		
		return ResponseEntity.ok(equipmentMapper.equipmentToEquipmentsDtos(equipmentService.findAll()));
	}
	
	@GetMapping("{name}")
	public ResponseEntity<EquipmentDTO> findByName(@PathVariable String name) {
		
		return ResponseEntity.ok(equipmentMapper.equipmentToEquipmentDto(equipmentService.findByName(name)));
	}
	
	@PostMapping
	public ResponseEntity<Equipment> create(@RequestBody Equipment equipment) {
		
		equipmentService.save(equipment);
		
		return ResponseEntity.status(HttpStatus.CREATED).body(equipment);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> delete(@PathVariable Long id) {
		
		if (equipmentService.isEquipmentExists(id)) {
			equipmentService.deleteById(id);
			return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
		} else {
			return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).build();
		}
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<Equipment> update(@PathVariable Long id, @RequestBody Equipment equipment) {
			
		if (!equipmentService.isEquipmentExists(id)) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(equipment);
		}
		
		equipment.setId(id);
		equipmentService.save(equipment);
		
		return ResponseEntity.status(HttpStatus.ACCEPTED).body(equipment);
		
	}
	

}
