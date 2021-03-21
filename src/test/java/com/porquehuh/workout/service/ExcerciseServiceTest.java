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
import com.porquehuh.workout.domain.Excercise;
import com.porquehuh.workout.domain.Muscle;
import com.porquehuh.workout.repository.ExcerciseRepository;

@ExtendWith(MockitoExtension.class)
class ExcerciseServiceTest {

	@InjectMocks
	private ExcerciseService excerciseService;
	
	@Mock
	ExcerciseRepository excerciseRepository;
	
	private Excercise excercise;
	private Muscle muscle;
	private Equipment equipment;
	
	@BeforeEach
	void setUp() throws Exception {
		muscle = new Muscle();
		muscle.setId(1L);
		muscle.setName("Biceps brachii");
		
		equipment = new Equipment();
		equipment.setId(5L);
		equipment.setName("Dumbbell");
		
		excercise = new Excercise();
		excercise.setId(1L);
		excercise.setName("Curl");
		excercise.setDescription("Lift up");
		excercise.setEquipment(equipment);
		excercise.setMuscle(muscle);
		
	}
	
	@Test
	@DisplayName("Finding all Excercices")
	void testFindAll() {
		
		List<Excercise> list = new ArrayList<Excercise>();
		Excercise exc2 = new Excercise();
		exc2.setId(2L);
		exc2.setName("Push up");
		exc2.setDescription("Push up and down");
		exc2.setEquipment(equipment);
		exc2.setMuscle(muscle);
		
		list.add(excercise);
		list.add(exc2);
		
		when(excerciseRepository.findAll()).thenReturn(list);
		
		List<Excercise> excerciseList = excerciseService.findAll();
		
		assertEquals(2, excerciseList.size());
		verify(excerciseRepository, times(1)).findAll();
	}

	@Test
	@DisplayName("Finding excercise by id")
	void testFindById() {
		
		when(excerciseRepository.findById(1L)).thenReturn(Optional.of(excercise));
		
		Excercise exc = excerciseService.findById(1L);
		
		assertEquals(1l, exc.getId());
	}

	@Test
	@DisplayName("finding excercise by name")
	void testFindByName() {
		
		when(excerciseRepository.findByName("Curl")).thenReturn(excercise);
		
		Excercise exc = excerciseService.findByName("Curl");
		
		assertEquals("Curl", exc.getName());
	}

	@Test
	@DisplayName("Saving excercise")
	void testSave() {
		
		excerciseService.save(excercise);
		verify(excerciseRepository, times(1)).save(excercise);
	}

	@Test
	@DisplayName("Does excercise exist")
	void testIsExcerciseExist() {
		
		when(excerciseRepository.existsById(1L)).thenReturn(true);
		
		boolean isExist = excerciseService.isExcerciseExist(1L);
		
		assertTrue(isExist);
	}

	@Test
	@DisplayName("Delete excercise by id")
	void testDeleteById() {
		
		excerciseService.deleteById(1L);
		verify(excerciseRepository, times(1)).deleteById(1L);
	}

}
