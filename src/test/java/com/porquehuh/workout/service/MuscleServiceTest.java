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

import com.porquehuh.workout.domain.Muscle;
import com.porquehuh.workout.repository.MuscleRepository;

@ExtendWith(MockitoExtension.class)
class MuscleServiceTest {
	
	@InjectMocks
	private MuscleService muscleService;
	
	@Mock
	MuscleRepository muscleRepository;
	
	private Muscle muscle;
	
	@BeforeEach
	void setUp() throws Exception {
		muscle = new Muscle();
		muscle.setId(1L);
		muscle.setName("Biceps brachii");
	}

	@Test
	@DisplayName("Finding all muscles")
	void testFindAll() {
		
		List<Muscle> list = new ArrayList<Muscle>();
		Muscle m2 = new Muscle(2L, "Alterior deltoid");
		Muscle m3 = new Muscle(3L, "Back");
		
		list.add(muscle);
		list.add(m2);
		list.add(m3);
		
		when(muscleRepository.findAll()).thenReturn(list);
		
		List<Muscle> muscleList = muscleService.findAll();
		
		assertEquals(3, muscleList.size());
		verify(muscleRepository, times(1)).findAll();
		
	}

	@Test
	@DisplayName("Finding muscle by id")
	void testFindById() {
		
		when(muscleRepository.findById(1L)).thenReturn(Optional.of(muscle));
		
		Muscle muscle1 = muscleService.findById(1L);
		
		assertEquals(1L, muscle1.getId());
	}

	@Test
	@DisplayName("Finding muscle by name")
	void testFindByName() {
		
		when(muscleRepository.findByName("Biceps brachii")).thenReturn(muscle);
		
		Muscle muscle1 = muscleService.findByName("Biceps brachii");
		
		assertEquals("Biceps brachii", muscle1.getName());
	}

	@Test
	@DisplayName("Saving muscle")
	void testSave() {
		
		muscleService.save(muscle);
		verify(muscleRepository, times(1)).save(muscle);
	}

	@Test
	@DisplayName("Deleting muscle by id")
	void testDeleteById() {
		
		muscleService.deleteById(1L);
		verify(muscleRepository, times(1)).deleteById(1L);
	}

	@Test
	@DisplayName("Does muscle exist")
	void testIsMuscleExists() {
		
		when(muscleRepository.existsById(1L)).thenReturn(true);
		
		boolean isExist = muscleService.isMuscleExists(1L);
		
		assertTrue(isExist);
	}

}
