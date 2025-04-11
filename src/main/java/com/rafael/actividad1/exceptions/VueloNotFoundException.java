package com.rafael.actividad1.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class VueloNotFoundException extends ResourceNotFoundException {

    public VueloNotFoundException(String message) {
        super(message);
    }
}
