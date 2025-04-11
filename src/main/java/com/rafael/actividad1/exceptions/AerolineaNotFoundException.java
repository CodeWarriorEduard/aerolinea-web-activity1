package com.rafael.actividad1.exceptions;

public class AerolineaNotFoundException extends ResourceNotFoundException {
    public AerolineaNotFoundException(String message) {
        super(message);
    }
}
