package com.porquehuh.workout.mapper;

import java.util.List;

import org.mapstruct.CollectionMappingStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import com.porquehuh.workout.domain.Excercise;
import com.porquehuh.workout.dto.ExcerciseDTO;

@Mapper(uses= {MuscleMapper.class , EquipmentMapper.class} , componentModel="spring", collectionMappingStrategy = CollectionMappingStrategy.ADDER_PREFERRED)
public interface ExcerciseMapper {
	
	ExcerciseMapper INSTANCE = Mappers.getMapper( ExcerciseMapper.class );
	
	@Mapping(source="excercise.id" , target="id")
	@Mapping(source="excercise.name" , target="name")
	@Mapping(source="excercise.description" , target="description")
	@Mapping(source="muscle.name", target="muscle")
	@Mapping(source="equipment.name", target="equipment")
	ExcerciseDTO excerciseToExcerciseDto(Excercise excercise);
	
	@Mapping(source="excercise.id" , target="id")
	@Mapping(source="excercise.name" , target="name")
	@Mapping(source="excercise.description" , target="description")
	@Mapping(source="muscle.name", target="muscle")
	@Mapping(source="equipment.name", target="equipment")
	List<ExcerciseDTO> excercisesToExcercisesDtos(List<Excercise> excercises);
		
}
