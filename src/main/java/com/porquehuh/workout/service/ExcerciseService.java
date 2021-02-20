package com.porquehuh.workout.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.porquehuh.workout.domain.Excercise;
import com.porquehuh.workout.repository.ExcerciseRepository;

@Service
public class ExcerciseService {
	
	@Autowired
	private ExcerciseRepository excerciseRepository;
	
	public List<Excercise> findAll() {
		List<Excercise> excercises = new ArrayList<>();
		excerciseRepository.findAll().forEach(excercises::add);
		return excercises;
	}
	
	public Excercise findById(Long id) {
		return excerciseRepository.findById(id).get();
	}
	
	public Excercise findByName(String name) {
		Excercise excersice = excerciseRepository.findByName(name);
		return excersice;
	}
	
	public Excercise save(Excercise excercise) {
		return excerciseRepository.save(excercise);
	}
	
	public boolean isExcerciseExist(Long id) {
		return excerciseRepository.existsById(id);
	}
	
	public void deleteById(Long id) {
		excerciseRepository.deleteById(id);
	}
	

}
