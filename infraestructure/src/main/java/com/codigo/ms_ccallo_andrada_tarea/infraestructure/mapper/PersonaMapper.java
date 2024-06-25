package com.codigo.ms_ccallo_andrada_tarea.infraestructure.mapper;

import com.codigo.ms_ccallo_andrada_tarea.domain.aggregates.dto.PersonaDTO;
import com.codigo.ms_ccallo_andrada_tarea.infraestructure.entity.PersonaEntity;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;

@Service
public class PersonaMapper {
    private static  final ModelMapper modelMapper=new ModelMapper();

    public PersonaDTO mapToDto(PersonaEntity personaEntity){
        return modelMapper.map(personaEntity,PersonaDTO.class);
    }

     public PersonaEntity mapToEntity(PersonaDTO personaDTO){
        return modelMapper.map(personaDTO,PersonaEntity.class);
     }
    public List<PersonaDTO> mapToDto(List<PersonaEntity> personaEntities) {
        return personaEntities.stream()
                .map(personaEntity -> mapToDto(personaEntity))
                .collect(Collectors.toList());
    }
    public PersonaDTO mapToDtoUpdate (PersonaEntity personaEntity,PersonaDTO personaDTO){
        modelMapper.getConfiguration().setSkipNullEnabled(true);
        modelMapper.map(personaDTO, personaEntity);
        return modelMapper.map(personaEntity, PersonaDTO.class);
    }
}
