package com.rafael.actividad1.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class DefaultApiResponse <T>{
    private String status;
    private String message;
    private T data;
    private Object metadata;
}
