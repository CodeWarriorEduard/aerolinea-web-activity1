package com.rafael.actividad1.controller;

import com.rafael.actividad1.dto.response.PasaporteResponseDTO;
import com.rafael.actividad1.response.DefaultApiResponse;
import com.rafael.actividad1.service.PasaporteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/pasaportes")
public class PasaporteController {

    private final PasaporteService pasaporteService;

    @Autowired
    public PasaporteController(PasaporteService pasaporteService) {
        this.pasaporteService = pasaporteService;
    }

    @GetMapping("/por-numero")
    public ResponseEntity<DefaultApiResponse<PasaporteResponseDTO>> getPasaporteByNumero
            (@RequestParam String numero) {
        PasaporteResponseDTO response = pasaporteService.findByNumero(numero);

        DefaultApiResponse apiResponse = new DefaultApiResponse(
                null,
                "Pasaporte encontrado",
                response,
                null
        );

        return ResponseEntity.ok(apiResponse);
    }

    @GetMapping("/por-id-pasajero")
    public ResponseEntity<DefaultApiResponse<PasaporteResponseDTO>> getPasaporteByPasajeroId(
            @RequestParam Long pasajeroId) {

        PasaporteResponseDTO response = pasaporteService.findByPasajeroId(pasajeroId);

        DefaultApiResponse apiResponse = new DefaultApiResponse(
                null,
                "Pasaporte encontrado",
                response,
                null
        );

        return ResponseEntity.ok(apiResponse);
    }

    @GetMapping("/existe-por-numero")
    public ResponseEntity<DefaultApiResponse<Boolean>> existsPasaporteByNumero(@RequestParam String numero) {
        Boolean response = pasaporteService.existsByNumero(numero);
        DefaultApiResponse apiResponse = new DefaultApiResponse(
          null,
          "Existe el pasaporte con el numero: " +numero,
          response,
          null
        );
        return ResponseEntity.ok(apiResponse);
    }

    @GetMapping("/por-nombre-pasajero")
    public ResponseEntity<DefaultApiResponse<List<PasaporteResponseDTO>>> getPasaportesByNombrePasajero(@RequestParam String nombre) {
        List<PasaporteResponseDTO> response = pasaporteService.findByPasajeroNombre(nombre);
        DefaultApiResponse apiResponse = new DefaultApiResponse(
                null,
                "Pasaportes con un pasajero con el nombre: " + nombre,
                response,
                null
        );
        return ResponseEntity.ok(apiResponse);
    }

    @DeleteMapping("/borrar-por-numero")
    public ResponseEntity<DefaultApiResponse<Boolean>> borrarPasaporte(@RequestParam String numero) {
        Boolean response = (pasaporteService.deleteByNumero(numero) == 1);
        DefaultApiResponse apiResponse = new DefaultApiResponse(
          null,
          "Pasporte eliminado",
          response,
          null
        );
        return ResponseEntity.ok(apiResponse);
    }

    @GetMapping("/por-pasajero-nid")
    public ResponseEntity<DefaultApiResponse<PasaporteResponseDTO>> getPasaporteByPasajeroNid
            (@RequestParam String nid) {
        PasaporteResponseDTO response = pasaporteService.findByPasajeroNId(nid);
        DefaultApiResponse apiResponse = new DefaultApiResponse(
          null,
          "Pasaporte encontrado",
          response,
          null
        );
        return ResponseEntity.ok(apiResponse);
    }

    @GetMapping("/por-numero-parcial")
    public ResponseEntity<DefaultApiResponse<List<PasaporteResponseDTO>>> getPasaportesByNumeroParcial
            (@RequestParam String numero) {
        List<PasaporteResponseDTO> response = pasaporteService.searchByPartialNumber(numero);
        DefaultApiResponse apiResponse = new DefaultApiResponse(
          null,
          "Pasaportes que contienen el numero: "+numero,
          response,
          null
        );
        return ResponseEntity.ok(apiResponse);
    }

    @GetMapping("/por-nombre-nid-pasajero")
    public ResponseEntity<DefaultApiResponse<PasaporteResponseDTO>> getPasaporteByPasajeroNombreAndNid
            (@RequestParam String nombre, @RequestParam String nid) {
        PasaporteResponseDTO response = pasaporteService.findByPasajeroNombreAndNid(nombre, nid);
        DefaultApiResponse apiResponse = new DefaultApiResponse(
          null,
          "Pasaporte encontrado",
          response,
          null
        );
        return ResponseEntity.ok(apiResponse);
    }

    @GetMapping("/existe-por-pasajero-nid")
    public ResponseEntity<DefaultApiResponse<Boolean>> existsPasaporteByNid(@RequestParam String nid) {
        Boolean response = pasaporteService.existsByPasajeroNid(nid);
        DefaultApiResponse apiResponse = new DefaultApiResponse(
          null,
          "Existe pasaporte con un pasajero con el nid: "+nid,
          response,
          null
        );
        return ResponseEntity.ok(apiResponse);
    }

    @DeleteMapping("/borrar-por-pasajero-nid")
    public ResponseEntity<DefaultApiResponse<Boolean>> borrarPasaporteByNid(@RequestParam String nid) {
        Boolean response = (pasaporteService.deleteByPasajeroNid(nid) == 1);
        DefaultApiResponse apiResponse = new DefaultApiResponse(
                null,
                "Pasaporte eliminado",
                response,
                null
        );
        return ResponseEntity.ok(apiResponse);
    }

}
