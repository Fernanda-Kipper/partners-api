package com.example.partnersapi.infra.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

public class BadRequestException extends Exception {
    String message;

    public BadRequestException(String message){
        this.message = message;
    }
}
