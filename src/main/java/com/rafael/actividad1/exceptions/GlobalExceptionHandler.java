package com.rafael.actividad1.exceptions;

import com.rafael.actividad1.response.DefaultApiResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {


    @ExceptionHandler(value = AerolineaNotFoundException.class)
    public ResponseEntity<DefaultApiResponse<Object>> aerolineaNotFoundExceptionHandler(AerolineaNotFoundException e){
        DefaultApiResponse<Object> apiResponse = new DefaultApiResponse<>(
                "404 NOT FOUND",
                e.getMessage(),
                null,
                null
        );
        return new ResponseEntity<>(apiResponse, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(PasajeroNotFoundException.class)
    public ResponseEntity<DefaultApiResponse<Object>> pasajeroNotFoundExceptionHandler
            (PasajeroNotFoundException e){
        DefaultApiResponse<Object> apiResponse = new DefaultApiResponse<>(
                "404 NOT FOUND",
                e.getMessage(),
                null,
                null
        );
        return new ResponseEntity<>(apiResponse, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(PasaporteNotFoundException.class)
    public ResponseEntity<DefaultApiResponse<Object>> pasaporteNotFoundExceptionHandler
            (PasaporteNotFoundException e){
        DefaultApiResponse<Object> apiResponse = new DefaultApiResponse<>(
                "404 NOT FOUND",
                e.getMessage(),
                null,
                null
        );
        return new ResponseEntity<>(apiResponse, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(ReservaNotFoundException.class)
    public ResponseEntity<DefaultApiResponse<Object>> reservaNotFoundExceptionHandler
            (ReservaNotFoundException e){
        DefaultApiResponse<Object> apiResponse = new DefaultApiResponse<>(
                "404 NOT FOUND",
                e.getMessage(),
                null,
                null
        );
        return new ResponseEntity<>(apiResponse, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(VueloNotFoundException.class)
    public ResponseEntity<DefaultApiResponse<Object>> vueloNotFoundExceptionHandler
            (VueloNotFoundException e){
        DefaultApiResponse<Object> apiResponse = new DefaultApiResponse<>(
                "404 NOT FOUND",
                e.getMessage(),
                null,
                null
        );
        return new ResponseEntity<>(apiResponse, HttpStatus.NOT_FOUND);
    }

}
