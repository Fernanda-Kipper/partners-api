package com.example.partnersapi.infra.exceptions;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ControllersExceptionHandler {
    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity threat404(){
        return ResponseEntity.notFound().build();
    }

    @ExceptionHandler(BadRequestException.class)
    public ResponseEntity<ExceptionDTO> threatBadRequest(BadRequestException exception){
        ExceptionDTO response = new ExceptionDTO(exception.getMessage(), 400);
        return ResponseEntity.badRequest().body(response);
    }

    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<ExceptionDTO> threatInternalServerError(){
        ExceptionDTO response = new ExceptionDTO("Internal Server Error", 500);
        return ResponseEntity.internalServerError().body(response);
    }
}

