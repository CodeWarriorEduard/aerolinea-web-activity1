package com.rafael.actividad1.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class ReservaNotFoundException extends ResourceNotFoundException {

    public ReservaNotFoundException(String message) {
        super(message);
    }
}
