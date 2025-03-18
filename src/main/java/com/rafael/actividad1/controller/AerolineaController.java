package com.rafael.actividad1.controller;

import com.rafael.actividad1.entity.Aerolinea;
import com.rafael.actividad1.service.AerolineaService;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/apiv1/aerolinea")
public class AerolineaController {

    @Autowired
    private final AerolineaService aerolineaService;

    public AerolineaController(AerolineaService aerolineaService) {
        this.aerolineaService = aerolineaService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<Aerolinea> obtenerAerolineaPorId(@PathVariable Long id){
        Optional<Aerolinea> response = aerolineaService.findAerolineaById(id);
        return response.isPresent()? ResponseEntity.ok().body(response.get()): ResponseEntity.notFound().build();
    }

    @PostMapping("/guardar")
    public Aerolinea guardarAerolinea(@RequestBody Aerolinea aerolinea){ // Reemplazar con dtos
        return aerolineaService.saveAerolinea(aerolinea);
    }

    @GetMapping("/todas")
    public ResponseEntity<List<Aerolinea>> obtenerTodasLasAerolineas(){
        List<Aerolinea> response = aerolineaService.findAllAerolineasInDb();
        return response.isEmpty()? ResponseEntity.noContent().build(): ResponseEntity.ok().body(response);
    }

    @GetMapping("/por-nombre")
    public ResponseEntity<Aerolinea> obtenerAerolineaPorNombre(@RequestParam String nombre){
        Optional<Aerolinea> response = aerolineaService.findAerolineaByNombre(nombre);
        return response.isEmpty()? ResponseEntity.notFound().build() : ResponseEntity.ok().body(response.get());
    }

    @GetMapping("/inicia-con-letra-a")
    public ResponseEntity<List<Aerolinea>> aerolineasQueEmpiezanPorA(){
        List<Aerolinea> response = aerolineaService.aerolineaStartsWithA();
        return response.isEmpty()? ResponseEntity.noContent().build(): ResponseEntity.ok().body(response);
    }

    @GetMapping("/por-pasajero")
    public ResponseEntity<List<Aerolinea>> obtenerAerolineaPorNombrePasajero(@RequestParam String nombre){
        List<Aerolinea> response = aerolineaService.findAerolineaByPassengerName(nombre);
        return response.isEmpty()? ResponseEntity.noContent().build(): ResponseEntity.ok().body(response);
    }


    @GetMapping("/dos-vuelos")
    public ResponseEntity<List<Aerolinea>> aerolineasQueTienenDosVuelos(){
        List<Aerolinea> response = aerolineaService.aerolineasWithTwoFlightsx();
        return response.isEmpty()? ResponseEntity.noContent().build(): ResponseEntity.ok().body(response);
    }


    @GetMapping("/todas/por-nombre")
    public ResponseEntity<List<Aerolinea>> obtenerAerolineasOrdenadasPorNombre(){
        List<Aerolinea> response = aerolineaService.findAllOrderedByName();
        return response.isEmpty()? ResponseEntity.noContent().build(): ResponseEntity.ok().body(response);
    }

    @GetMapping("/total")
    public ResponseEntity<Long> totalDeAerolineas(){
        return ResponseEntity.ok().body(aerolineaService.countAllAerolineas());
    }

}
