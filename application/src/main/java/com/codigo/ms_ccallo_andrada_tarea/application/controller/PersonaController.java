package com.codigo.ms_ccallo_andrada_tarea.application.controller;

import com.codigo.ms_ccallo_andrada_tarea.domain.aggregates.dto.PersonaDTO;
import com.codigo.ms_ccallo_andrada_tarea.domain.aggregates.request.RequestPersona;
import com.codigo.ms_ccallo_andrada_tarea.domain.ports.in.PersonaServiceIn;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/persona")
@RequiredArgsConstructor
public class PersonaController {

    private final PersonaServiceIn personaServiceIn;

    @PostMapping
    public ResponseEntity<PersonaDTO> registrar(@RequestBody RequestPersona persona){
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(personaServiceIn.createPersonaIn(persona));
    }

    @GetMapping
    public ResponseEntity<List<PersonaDTO>> getAll(){
        return ResponseEntity
                .status(HttpStatus.ACCEPTED)
                .body(personaServiceIn.getAllPersonasIn());
    }

    @GetMapping("/inactive")
    public ResponseEntity<List<PersonaDTO>> getAllNoActive(){
        return ResponseEntity
                .status(HttpStatus.ACCEPTED)
                .body(personaServiceIn.getAllPersonasNoActivoIn());
    }

    @GetMapping("id/{id}")
    public ResponseEntity<PersonaDTO> getById(@PathVariable Long id){
        return ResponseEntity
                .status(HttpStatus.ACCEPTED)
                .body(personaServiceIn.getByIdPersonaIn(id));
    }

    @GetMapping("numDoc/{numDoc}")
    public ResponseEntity<PersonaDTO> getByNumDoc(@PathVariable String numDoc){
        return ResponseEntity
                .status(HttpStatus.ACCEPTED)
                .body(personaServiceIn.getByNumDocPersonaIn(numDoc));
    }

    @PutMapping("numDoc/{numDoc}")
    public ResponseEntity<PersonaDTO> updateByNumDoc(@PathVariable String numDoc,@RequestBody PersonaDTO personaDTO){
        return ResponseEntity
                .status(HttpStatus.ACCEPTED)
                .body(personaServiceIn.updateByNumDocPersonaIn(numDoc,personaDTO));
    }

    @DeleteMapping("numDoc/{numDoc}")
    public ResponseEntity<String> deleteByNumDoc(@PathVariable String numDoc){
        personaServiceIn.deleteByNumDocPersonaIn(numDoc);
       return ResponseEntity
                .status(HttpStatus.ACCEPTED).body("Se borro con existo");

    }

}
