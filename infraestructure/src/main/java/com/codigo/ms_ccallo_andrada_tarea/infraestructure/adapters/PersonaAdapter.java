package com.codigo.ms_ccallo_andrada_tarea.infraestructure.adapters;

import com.codigo.ms_ccallo_andrada_tarea.domain.aggregates.constants.Constants;
import com.codigo.ms_ccallo_andrada_tarea.domain.aggregates.dto.PersonaDTO;
import com.codigo.ms_ccallo_andrada_tarea.domain.aggregates.exception.AlreadyExistsException;
import com.codigo.ms_ccallo_andrada_tarea.domain.aggregates.exception.NotFoundException;
import com.codigo.ms_ccallo_andrada_tarea.domain.aggregates.request.RequestPersona;
import com.codigo.ms_ccallo_andrada_tarea.domain.aggregates.response.ResponseReniec;
import com.codigo.ms_ccallo_andrada_tarea.domain.ports.out.PersonaServiceOut;
import com.codigo.ms_ccallo_andrada_tarea.infraestructure.dao.PersonaRepository;
import com.codigo.ms_ccallo_andrada_tarea.infraestructure.entity.PersonaEntity;
import com.codigo.ms_ccallo_andrada_tarea.infraestructure.mapper.PersonaMapper;
import com.codigo.ms_ccallo_andrada_tarea.infraestructure.rest.ClienteReniec;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.nio.file.OpenOption;
import java.sql.Timestamp;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PersonaAdapter implements PersonaServiceOut {
    private final PersonaRepository personaRepository;
    private final PersonaMapper personaMapper;
    private final ClienteReniec clienteReniec;

    @Value("${token.api}")
    private String token;

    private PersonaEntity getPersonaEntity(RequestPersona requestPersona){
        ResponseReniec responseReniec = getExec(requestPersona.getNumDoc());
        PersonaEntity personaEntity = new PersonaEntity();
        personaEntity.setNumDoc(responseReniec.getNumeroDocumento());
        personaEntity.setNombres(responseReniec.getNombres());
        personaEntity.setApePat(responseReniec.getApellidoPaterno());
        personaEntity.setApeMat(responseReniec.getApellidoMaterno());
        personaEntity.setEstado(Constants.ESTADO_ACTIVO);
        personaEntity.setUsuaCrea(Constants.USU_ADMIN);
        personaEntity.setDateCreate(getTime());
        return personaEntity;

    }

    private Timestamp getTime(){
        long current = System.currentTimeMillis();
        return new Timestamp(current);
    }
    private ResponseReniec getExec(String numero){
        String head = "Bearer "+token;
        ResponseReniec responseReniec = clienteReniec.getInfoReniec(numero,head);
        return responseReniec;
    }

    @Override
    public PersonaDTO createPersonaOut(RequestPersona requestPersona) {
        PersonaEntity personaExists=personaRepository.findByNumDoc(requestPersona.getNumDoc());
        if(personaExists!=null){
            throw new AlreadyExistsException("Persona ya existe ID ->"+requestPersona.getNumDoc());
        }else {
            PersonaEntity personaEntity = getPersonaEntity(requestPersona);
            return personaMapper.mapToDto(personaRepository.save(personaEntity));
        }

    }

    @Override
    public PersonaDTO getByIdPersonaOut(Long id) {

        PersonaEntity personaEntity = personaRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Persona no encontrada ID -> " + id));
        return personaMapper.mapToDto(personaEntity);
}
    @Override
    public PersonaDTO getByNumDocPersonaOut(String numDoc) {
    PersonaEntity personaEntity=personaRepository.findByNumDoc(numDoc);
    if(personaEntity==null){
    throw new NotFoundException("Persona no encontrada numDoc -> " + numDoc);
    }


        return personaMapper.mapToDto(personaEntity);
    }

    @Override
    public List<PersonaDTO> getAllPersonasOut() {
        List<PersonaEntity> personaEntities = personaRepository.findAll();
        return personaMapper.mapToDto(personaEntities.stream().filter(entity->entity.getEstado()==Constants.ESTADO_ACTIVO).collect(Collectors.toList()));
    }

    @Override
    public List<PersonaDTO> getAllPersonasNoActivoIn() {
        List<PersonaEntity> personaEntities = personaRepository.findAll();
        return personaMapper.mapToDto(personaEntities.stream().filter(entity->entity.getEstado()==Constants.ESTADO_NO_ACTIVO).collect(Collectors.toList()));
    }

    @Override
    public PersonaDTO updateByNumDocPersonaOut(String numDoc, PersonaDTO personaDTO) {
        PersonaEntity personaEntity=personaRepository.findByNumDoc(numDoc);
        if (personaEntity!=null){
            personaEntity.setDateModif(getTime());
            personaEntity.setUsuaModif(Constants.USU_ADMIN);
           PersonaDTO personaDTOActulizada=  personaMapper.mapToDtoUpdate(personaEntity,personaDTO);
            personaRepository.save(personaMapper.mapToEntity(personaDTOActulizada));
            return  personaDTOActulizada;
        }else {
            throw new NotFoundException("Persona no encontrada numDoc -> " + numDoc);
        }

    }

    @Override
    public void deleteByNumDocPersonaOut(String numDoc) {
        PersonaEntity personaEntity=personaRepository.findByNumDoc(numDoc);
        if(personaEntity!=null){
            personaEntity.setEstado(Constants.ESTADO_NO_ACTIVO);
            personaEntity.setDateDelet(getTime());
            personaEntity.setUsuaDelet(Constants.USU_ADMIN);
            personaRepository.save(personaEntity);
        }else {
            throw new NotFoundException("Persona no encontrada numDoc -> " + numDoc);
        }
    }
}
