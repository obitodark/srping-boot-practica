    package com.codigo.ms_ccallo_andrada_tarea.domain.aggregates.exception;

    import org.springframework.http.HttpStatus;
    import org.springframework.http.ResponseEntity;
    import org.springframework.web.bind.annotation.ControllerAdvice;
    import org.springframework.web.bind.annotation.ExceptionHandler;
    import org.springframework.web.context.request.WebRequest;

    @ControllerAdvice
    public class GlobalExceptionHandler {

        @ExceptionHandler(NotFoundException.class)
        public ResponseEntity<Object> handleNotFoundException(
                NotFoundException ex, WebRequest request) {
            ErrorResponse errorResponse = ErrorResponse.builder()
                            .status(HttpStatus.NOT_FOUND.value())
                            .message("Not Found")
                            .details(ex.getMessage())
                            .path(request.getDescription(false))
                            .build();

            return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);
        }

        @ExceptionHandler(AlreadyExistsException.class)
        public ResponseEntity<Object> handleAlreadyExistsException(
                AlreadyExistsException  ex, WebRequest request) {
            ErrorResponse errorResponse = ErrorResponse.builder()
                    .status(HttpStatus.CONFLICT.value())
                    .message("Conflict")
                    .details(ex.getMessage())
                    .path(request.getDescription(false))
                    .build();
            return new ResponseEntity<>(errorResponse, HttpStatus.CONFLICT);
        }
        @ExceptionHandler(Exception.class)
        public ResponseEntity<Object> handleAllExceptions(
                Exception ex, WebRequest request) {
            ErrorResponse errorResponse = ErrorResponse.builder()
                    .status(HttpStatus.INTERNAL_SERVER_ERROR.value())
                    .message("Internal Server Error")
                    .details(ex.getMessage())
                    .path(request.getDescription(false))
                    .build();
            return new ResponseEntity<>(errorResponse, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
