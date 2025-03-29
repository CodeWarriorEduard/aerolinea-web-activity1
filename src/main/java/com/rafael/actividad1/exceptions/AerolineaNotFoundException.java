package com.rafael.actividad1.exceptions;

public class AerolineaNotFoundException extends RuntimeException {
    public AerolineaNotFoundException(String message) {
        super(message);
    }
}
