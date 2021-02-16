package com.porquehuh.workout.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.porquehuh.workout.domain.Equipment;
import com.porquehuh.workout.repository.EquipmentRepository;

@Service
public class EquipmentService {
	
	@Autowired
	private  EquipmentRepository equipmentRepository;
	
	public List<Equipment> findAll() {
		List<Equipment> equipments = new ArrayList<>();
		equipmentRepository.findAll().forEach(equipments::add);
		return equipments;
	}

	public Equipment findByName(String name) {
		Equipment equipment = equipmentRepository.findByName(name);
		return equipment;
	}
	
}
