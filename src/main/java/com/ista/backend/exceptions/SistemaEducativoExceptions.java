package com.ista.backend.exceptions;

import lombok.Data;
import org.springframework.http.HttpStatus;

@Data
public class SistemaEducativoExceptions extends RuntimeException{

    private String message;
    private HttpStatus httpStatus;

    public SistemaEducativoExceptions(String message,HttpStatus httpStatus){
        super(message);
        this.message=message;
        this.httpStatus=httpStatus;
    }
}
