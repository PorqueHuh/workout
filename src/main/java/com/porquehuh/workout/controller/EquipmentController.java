package com.porquehuh.workout.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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

}
