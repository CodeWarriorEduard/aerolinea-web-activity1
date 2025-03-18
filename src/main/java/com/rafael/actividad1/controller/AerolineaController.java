package com.rafael.actividad1.controller;

import com.rafael.actividad1.entity.Aerolinea;
import com.rafael.actividad1.service.AerolineaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/apiv1/aerolinea")
public class AerolineaController {

    @Autowired
    private final AerolineaService aerolineaService;

    public AerolineaController(AerolineaService aerolineaService) {
        this.aerolineaService = aerolineaService;
    }

    @GetMapping("/passenger")
    public List<Aerolinea> aerolineasByName(@RequestParam String nombre){
        return aerolineaService.findAerolineaByPassengerName(nombre);
    }
}
