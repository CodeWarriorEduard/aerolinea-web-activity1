package com.rafael.actividad1.controller;

import com.rafael.actividad1.dto.response.AerolineaResponseDTO;
import com.rafael.actividad1.dto.response.AerolineaVuelosResponseDTO;
import com.rafael.actividad1.entity.Aerolinea;
import com.rafael.actividad1.response.DefaultApiResponse;
import com.rafael.actividad1.service.AerolineaService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/aerolinea")
public class AerolineaController {

    @Autowired
    private final AerolineaService aerolineaService;

    public AerolineaController(AerolineaService aerolineaService) {
        this.aerolineaService = aerolineaService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<DefaultApiResponse<AerolineaResponseDTO>> obtenerAerolineaPorId(@PathVariable Long id){

        AerolineaResponseDTO response = aerolineaService.findAerolineaById(id);

        DefaultApiResponse<AerolineaResponseDTO> apiResponse = new DefaultApiResponse<>(
                "OK",
                "Aerolinea encontrada",
                response,
                null
        );
        return ResponseEntity.ok().body(apiResponse);
    }

    @PostMapping("/nueva")
    public ResponseEntity<DefaultApiResponse<AerolineaResponseDTO>> guardarAerolinea(@Valid  @RequestBody Aerolinea aerolinea){ // Reemplazar con dtos

        AerolineaResponseDTO responseDTO =  aerolineaService.saveAerolinea(aerolinea);
        DefaultApiResponse<AerolineaResponseDTO> apiResponse = new DefaultApiResponse<>(
                "OK",
                "Aerolinea Guardada",
                responseDTO,
                null
        );
        return ResponseEntity.ok(apiResponse);
    }

    @GetMapping("/todas")
    public ResponseEntity<DefaultApiResponse<List<AerolineaResponseDTO>>> obtenerTodasLasAerolineas(){
        List<AerolineaResponseDTO> response = aerolineaService.findAllAerolineasInDb();
        DefaultApiResponse<List<AerolineaResponseDTO>> apiResponse = new DefaultApiResponse<>(
                "OK",
                "Aerolinea Guardada",
                response,
                null
        );
        return response.isEmpty()? ResponseEntity.noContent().build(): ResponseEntity.ok().body(apiResponse);
    }

    @GetMapping("/por-nombre")
    public ResponseEntity<AerolineaResponseDTO> obtenerAerolineaPorNombre(@RequestParam String nombre){
        return aerolineaService.findAerolineaByNombre(nombre)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/inicia-con-letra-a")
    public ResponseEntity<DefaultApiResponse<List<AerolineaResponseDTO>>> aerolineasQueEmpiezanPorA(){
        List<AerolineaResponseDTO> response = aerolineaService.aerolineaStartsWithA();
        DefaultApiResponse<List<AerolineaResponseDTO>> apiResponse = new  DefaultApiResponse<>(
                "OK",
                "Lista de aerolineas",
                response,
                null
        );
        return ResponseEntity.ok().body(apiResponse);

    }

    @GetMapping("/por-pasajero")
    public ResponseEntity<DefaultApiResponse<List<AerolineaResponseDTO>>> obtenerAerolineaPorNombrePasajero(@RequestParam String nombre){
        List<AerolineaResponseDTO> response = aerolineaService.findAerolineaByPassengerName(nombre);
        DefaultApiResponse<List<AerolineaResponseDTO>> apiResponse = new  DefaultApiResponse<>(
                "OK",
                "Lista de aerolineas",
                response,
                null
        );
        return ResponseEntity.ok().body(apiResponse);
    }


    @GetMapping("/dos-vuelos")
    public ResponseEntity<DefaultApiResponse<List<AerolineaVuelosResponseDTO>>>  aerolineasQueTienenDosVuelos(){
        List<AerolineaVuelosResponseDTO> response = aerolineaService.aerolineasWithTwoFlightsx();
        DefaultApiResponse<List<AerolineaVuelosResponseDTO>> apiResponse = new  DefaultApiResponse<>(
                "OK",
                "Lista de aerolineas con dos vuelos",
                response,
                null
        );
        return ResponseEntity.ok().body(apiResponse);
    }


    @GetMapping("/todas/por-nombre")
    public ResponseEntity<DefaultApiResponse<List<AerolineaResponseDTO>>> obtenerAerolineasOrdenadasPorNombre(){
        List<AerolineaResponseDTO> response = aerolineaService.findAllOrderedByName();
        DefaultApiResponse<List<AerolineaResponseDTO>> apiResponse = new  DefaultApiResponse<>(
                "OK",
                "Lista de aerolineas con dos vuelos",
                response,
                null
        );
        return ResponseEntity.ok().body(apiResponse);
    }

    @GetMapping("/total")
    public ResponseEntity<Long> totalDeAerolineas(){
        return ResponseEntity.ok().body(aerolineaService.countAllAerolineas());
    }

}
