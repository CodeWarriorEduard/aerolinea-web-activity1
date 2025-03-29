package com.rafael.actividad1.exceptions;

import com.rafael.actividad1.entity.Aerolinea;
import com.rafael.actividad1.response.DefaultApiResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {


    @ExceptionHandler(value = AerolineaNotFoundException.class)
    public ResponseEntity<DefaultApiResponse<Object>> AerolineaNotFoundExceptionHandler(AerolineaNotFoundException e){
        DefaultApiResponse<Object> apiResponse = new DefaultApiResponse<>(
                "NOT FOUND",
                e.getMessage(),
                null,
                null
        );
        return new ResponseEntity<>(apiResponse, HttpStatus.NOT_FOUND);
    }


}
