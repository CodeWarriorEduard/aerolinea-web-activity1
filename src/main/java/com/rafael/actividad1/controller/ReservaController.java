package com.rafael.actividad1.controller;

import com.rafael.actividad1.dto.response.ReservaResponseDTO;
import com.rafael.actividad1.response.DefaultApiResponse;
import com.rafael.actividad1.service.ReservaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/reserva")
public class ReservaController {

    @Autowired
    private final ReservaService reservaService;


    public ReservaController(ReservaService reservaService) {
        this.reservaService = reservaService;
    }

    @GetMapping()
    public ResponseEntity<DefaultApiResponse<ReservaResponseDTO>> findByCodigoReserva(@RequestParam UUID codigo){
        ReservaResponseDTO response = reservaService.findByCodigoReserva(codigo);
        DefaultApiResponse<ReservaResponseDTO> apiResponse = new DefaultApiResponse<>(
                "OK",
                "Reserva encontrada",
                response,
                null
        );

        return ResponseEntity.ok().body(apiResponse);
    }

    @GetMapping("/vuelo/{id}")
    public ResponseEntity<DefaultApiResponse<List<ReservaResponseDTO>>> findByVueloId(@PathVariable Long id){
        List<ReservaResponseDTO> response = reservaService.findByVueloId(id);
        DefaultApiResponse<List<ReservaResponseDTO>> apiResponse = new DefaultApiResponse<>(
                "OK",
                "Reservas:",
                response,
                null
        );

        return ResponseEntity.ok().body(apiResponse);
    }


    @GetMapping("/cantidad/vuelos/{id}")
    public ResponseEntity<DefaultApiResponse<Long>> countByVueloId(@PathVariable Long id){
        Long response = reservaService.countByVueloId(id);
        DefaultApiResponse<Long> apiResponse = new DefaultApiResponse<>(
                "OK",
                "Cantidad de reservas por vuelo:",
                response,
                null
        );

        return ResponseEntity.ok().body(apiResponse);
    }

    @GetMapping("/pasajero")
    public ResponseEntity<DefaultApiResponse<List<ReservaResponseDTO>>> findByPasajeroNid(@RequestParam String nId){
        List<ReservaResponseDTO> response = reservaService.findByPasajeroNid(nId);
        DefaultApiResponse<List<ReservaResponseDTO>> apiResponse = new DefaultApiResponse<>(
                "OK",
                "Reservas dado el numero de identificacion del pasajero:",
                response,
                null
        );

        return ResponseEntity.ok().body(apiResponse);
    }

    @DeleteMapping("/{codigo}")
    public ResponseEntity<DefaultApiResponse<Integer>> deleteByCodigo(@PathVariable UUID codigo){
        reservaService.deleteByCodigo(codigo);
        return ResponseEntity.noContent().build();
    }


}
