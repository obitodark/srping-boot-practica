package com.codigo.ms_ccallo_andrada_tarea.domain.impl;

import com.codigo.ms_ccallo_andrada_tarea.domain.aggregates.dto.PersonaDTO;
import com.codigo.ms_ccallo_andrada_tarea.domain.aggregates.request.RequestPersona;
import com.codigo.ms_ccallo_andrada_tarea.domain.ports.in.PersonaServiceIn;
import com.codigo.ms_ccallo_andrada_tarea.domain.ports.out.PersonaServiceOut;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PersonaServiceImpl  implements PersonaServiceIn {

    private  final PersonaServiceOut personaServiceOut;
    @Override
    public PersonaDTO createPersonaIn(RequestPersona requestPersona) {
        return personaServiceOut.createPersonaOut(requestPersona);
    }

    @Override
    public PersonaDTO getByIdPersonaIn(Long id) {
        return personaServiceOut.getByIdPersonaOut(id);
    }

    @Override
    public PersonaDTO getByNumDocPersonaIn(String numDoc) {
        return personaServiceOut.getByNumDocPersonaOut(numDoc);
    }

    @Override
    public List<PersonaDTO> getAllPersonasIn() {
        return personaServiceOut.getAllPersonasOut();
    }

    @Override
    public List<PersonaDTO> getAllPersonasNoActivoIn() {
        return personaServiceOut.getAllPersonasNoActivoIn();
    }

    @Override
    public PersonaDTO updateByNumDocPersonaIn(String numDoc, PersonaDTO personaDTO) {
        return personaServiceOut.updateByNumDocPersonaOut(numDoc,personaDTO);
    }

    @Override
    public void deleteByNumDocPersonaIn(String numDoc) {
    personaServiceOut.deleteByNumDocPersonaOut(numDoc);
    }
}
