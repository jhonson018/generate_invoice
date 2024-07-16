package com.practicas.opensource;

import org.springframework.http.HttpStatus;

public class InvoiceException extends RuntimeException{
    private String message;
    private HttpStatus httpStatus;

    public InvoiceException(String message, HttpStatus httpStatus){
        super(message);
        this.message = message;
        this.httpStatus = httpStatus;
    }
}
