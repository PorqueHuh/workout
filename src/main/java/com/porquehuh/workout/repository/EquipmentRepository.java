package com.porquehuh.workout.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.porquehuh.workout.domain.Equipment;

@Repository
public interface EquipmentRepository extends CrudRepository<Equipment, Long>{

}
