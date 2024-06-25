package com.codigo.ms_ccallo_andrada_tarea.infraestructure.dao;

import com.codigo.ms_ccallo_andrada_tarea.infraestructure.entity.PersonaEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;


public interface PersonaRepository extends JpaRepository<PersonaEntity,Long> {
PersonaEntity findByNumDoc(String numDoc);
}
