package com.example.partnersapi.infra.exceptions;

public class BadRequestException extends Exception {

    public BadRequestException(String message){
        super(message);
    }
}
