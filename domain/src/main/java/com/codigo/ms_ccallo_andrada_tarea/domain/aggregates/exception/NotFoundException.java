package com.codigo.ms_ccallo_andrada_tarea.domain.aggregates.exception;

public class NotFoundException extends RuntimeException{
    public NotFoundException(String message){
        super(message);
    }
}
