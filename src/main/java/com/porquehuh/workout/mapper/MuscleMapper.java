package com.porquehuh.workout.mapper;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;

import com.porquehuh.workout.domain.Muscle;
import com.porquehuh.workout.dto.MuscleDTO;

@Mapper(uses={ExcerciseMapper.class} , componentModel="spring")
public interface MuscleMapper {
	
	MuscleMapper INSTANCE =  Mappers.getMapper( MuscleMapper.class );
	
	@Mappings({
		@Mapping(source="muscle.exercise", target="excercise"),
	})
	List<MuscleDTO> musclesToMusclesDtos(List<Muscle> muscles);
	
	@Mapping(source = "name" , target = "name")
	MuscleDTO muscleToMuscleDto(Muscle muscle);
	
	@Mapping(source = "name" , target = "name")
	Muscle muscleDtoToMuscle(MuscleDTO muscleDto);

}
