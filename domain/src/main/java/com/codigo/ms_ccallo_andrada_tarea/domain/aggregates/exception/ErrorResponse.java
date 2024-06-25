package com.codigo.ms_ccallo_andrada_tarea.domain.aggregates.exception;

import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor

public class ErrorResponse {
    private int status;
    private String message;
    private String details;
    private String path;
}
