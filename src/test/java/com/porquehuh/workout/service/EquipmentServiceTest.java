package com.porquehuh.workout.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.porquehuh.workout.domain.Equipment;
import com.porquehuh.workout.repository.EquipmentRepository;

@ExtendWith(MockitoExtension.class)
class EquipmentServiceTest {
	
	@InjectMocks
	private EquipmentService equipmentService;
	
	@Mock
	EquipmentRepository equipmentRepository;
	
	private Equipment equipment;
	
	@BeforeEach
	void setUp() throws Exception {
		equipment = new Equipment();
		equipment.setId(5L);
		equipment.setName("Dumbbell");
	}
	
	@Test
	@DisplayName("Finding all equipment")
	void testFindAll() {
		
		List<Equipment> list = new ArrayList<Equipment>();
		Equipment eq1 = new Equipment(1L, "Dumbbell");
		Equipment eq2 = new Equipment(2L, "Yoga mat");
		Equipment eq3 = new Equipment(3L, "Ball");
		
		list.add(eq1);
		list.add(eq2);
		list.add(eq3);
		
		when(equipmentRepository.findAll()).thenReturn(list);
		
		List<Equipment> equipmentList  = equipmentService.findAll();
		
		assertEquals(3, equipmentList.size());
		verify(equipmentRepository, times(1)).findAll();
		
	}

	@Test
	@DisplayName("Finding equipment by id")
	void testFindById() {
		
		when(equipmentRepository.findById(5L)).thenReturn(Optional.of(equipment));
		
		Equipment eq1 = equipmentService.findById(5L);
		
		assertEquals(5L, eq1.getId());
	}

	@Test
	@DisplayName("Finding equipment by name")
	void testFindByName() {
		
		when(equipmentRepository.findByName("Dumbbell")).thenReturn(equipment);
		
		Equipment eq1 = equipmentService.findByName("Dumbbell");
		
		assertEquals("Dumbbell", eq1.getName());
	}

	@Test
	@DisplayName("Saving equipment with id and name")
	void testSave() {
		
		equipmentService.save(equipment);
		
		verify(equipmentRepository, times(1)).save(equipment);
	}

	@Test
	@DisplayName("Deleting equipment by id")
	void testDeleteById() {
		
		equipmentService.deleteById(5L);
		
		verify(equipmentRepository, times(1)).deleteById(5L);
	}

	@Test
	@DisplayName("Check if equipment exist boolean")
	void testIsEquipmentExists() {
		
		when(equipmentRepository.existsById(5L)).thenReturn(true);
		
		boolean isExist = equipmentService.isEquipmentExists(5L);
		
		assertTrue(isExist);
	}

}
