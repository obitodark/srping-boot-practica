package com.codigo.ms_ccallo_andrada_tarea.domain.ports.in;

import com.codigo.ms_ccallo_andrada_tarea.domain.aggregates.dto.PersonaDTO;
import com.codigo.ms_ccallo_andrada_tarea.domain.aggregates.request.RequestPersona;

import java.util.List;

public interface PersonaServiceIn {

    PersonaDTO createPersonaIn (RequestPersona requestPersona);
    PersonaDTO getByIdPersonaIn(Long id);
    PersonaDTO getByNumDocPersonaIn(String numDoc);
    List<PersonaDTO>getAllPersonasIn();
    List<PersonaDTO>getAllPersonasNoActivoIn();
    PersonaDTO updateByNumDocPersonaIn(String numDoc,PersonaDTO personaDTO);
    void deleteByNumDocPersonaIn(String numDoc);

}
