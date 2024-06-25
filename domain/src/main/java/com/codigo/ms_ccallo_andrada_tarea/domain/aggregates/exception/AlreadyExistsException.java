package com.codigo.ms_ccallo_andrada_tarea.domain.aggregates.exception;

public class AlreadyExistsException extends RuntimeException{
    public AlreadyExistsException(String message){
        super(message);
    }
}
