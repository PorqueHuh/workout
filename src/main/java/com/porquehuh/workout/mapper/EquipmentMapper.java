package com.porquehuh.workout.mapper;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;

import com.porquehuh.workout.domain.Equipment;
import com.porquehuh.workout.dto.EquipmentDTO;

@Mapper(componentModel="spring")
public interface EquipmentMapper {
	
	EquipmentMapper INSTANCE = Mappers.getMapper( EquipmentMapper.class);
	
	@Mappings({
		@Mapping(source = "name", target = "name")
	})
	List<EquipmentDTO> equipmentToEquipmentsDtos(List<Equipment> equipment);

	@Mapping(source="name" , target="name")
	EquipmentDTO equipmentToEquipmentDto(Equipment equipment);
	
}
