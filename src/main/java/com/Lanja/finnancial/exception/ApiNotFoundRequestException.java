package com.Lanja.finnancial.exception;

public class ApiNotFoundRequestException extends RuntimeException{

    public ApiNotFoundRequestException(String message) {
        super(message);
    }
}
