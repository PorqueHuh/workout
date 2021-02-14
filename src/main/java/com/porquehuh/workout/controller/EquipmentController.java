package com.porquehuh.workout.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.porquehuh.workout.domain.Equipment;
import com.porquehuh.workout.service.EquipmentService;

@RestController
public class EquipmentController {
	
	@Autowired
	private EquipmentService eqipmentService;
	
	@GetMapping("/equipment")
	List<Equipment> all() {
		return eqipmentService.findAll();
	}

}
