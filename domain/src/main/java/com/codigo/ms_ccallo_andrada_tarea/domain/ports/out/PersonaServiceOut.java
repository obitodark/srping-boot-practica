package com.codigo.ms_ccallo_andrada_tarea.domain.ports.out;

import com.codigo.ms_ccallo_andrada_tarea.domain.aggregates.dto.PersonaDTO;
import com.codigo.ms_ccallo_andrada_tarea.domain.aggregates.request.RequestPersona;

import java.util.List;

public interface PersonaServiceOut {
    PersonaDTO createPersonaOut(RequestPersona requestPersona);
     PersonaDTO getByIdPersonaOut(Long id);
     PersonaDTO getByNumDocPersonaOut(String numDoc);
    List<PersonaDTO> getAllPersonasOut();
    List<PersonaDTO>getAllPersonasNoActivoIn();
    PersonaDTO updateByNumDocPersonaOut(String numDoc,PersonaDTO personaDTO);
    void deleteByNumDocPersonaOut(String numDoc);
}
