package com.example.partnersapi.infra.exceptions;

import com.fasterxml.jackson.annotation.JsonAutoDetect;

@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
public class ExceptionDTO {
    String message;
    Integer status;
    public ExceptionDTO(String message, Integer status){
        this.message = message;
        this.status = status;
    }
}