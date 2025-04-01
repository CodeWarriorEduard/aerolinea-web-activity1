package com.rafael.actividad1.controller;

import com.rafael.actividad1.dto.response.PasajeroResponseDTO;
import com.rafael.actividad1.response.DefaultApiResponse;
import com.rafael.actividad1.service.PasajeroService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/pasajeros")
public class PasajeroController {

    private final PasajeroService pasajeroService;

    @Autowired
    public PasajeroController(PasajeroService pasajeroService) {
        this.pasajeroService = pasajeroService;
    }

    @GetMapping("/por-nombre")
    public ResponseEntity<DefaultApiResponse<List<PasajeroResponseDTO>>> getPasajerosByNombre(@RequestParam String nombre) {
        List<PasajeroResponseDTO> response = pasajeroService.findByNombre(nombre);
        DefaultApiResponse<List<PasajeroResponseDTO>> apiResponse = new DefaultApiResponse<>(
          null,
          "Pasajeros con el nombre: " + nombre,
          response,
          null
        );
        return response.isEmpty() ? ResponseEntity.noContent().build() : ResponseEntity.ok(apiResponse);
    }

    @GetMapping("/por-nid")
    public ResponseEntity<DefaultApiResponse<PasajeroResponseDTO>> getPasajeroByNid(@RequestParam String nid) {
        PasajeroResponseDTO response = pasajeroService.findByNid(nid);
        DefaultApiResponse<PasajeroResponseDTO> apiResponse = new DefaultApiResponse<>(
          null,
          "Pasajero encontrado",
          response,
          null
        );
        return ResponseEntity.ok(apiResponse);
    }

    @GetMapping("/por-nombre-contiene")
    public ResponseEntity<DefaultApiResponse<List<PasajeroResponseDTO>>> getPasajerosByNombreContaining
            (@RequestParam String nombre) {
        List<PasajeroResponseDTO> response = pasajeroService.findByNombreContaining(nombre);
        DefaultApiResponse<List<PasajeroResponseDTO>> apiResponse = new DefaultApiResponse<>(
                null,
                "Pasajeros con el nombre: " + nombre,
                response,
                null
        );
        return response.isEmpty() ? ResponseEntity.noContent().build() : ResponseEntity.ok(apiResponse);
    }

    @GetMapping("/existe-por-nid")
    public  ResponseEntity<DefaultApiResponse<Boolean>> existsPasajeroByNid(@RequestParam String nid) {
        Boolean response = pasajeroService.existsByNid(nid);
        DefaultApiResponse<Boolean> apiResponse = new DefaultApiResponse<>(
          null,
          "Existe pasajero con el nid: " +nid,
          response,
          null
        );
        return ResponseEntity.ok(apiResponse);
    }

    @GetMapping("/cuenta-por-nombre")
    public ResponseEntity<DefaultApiResponse<Integer>> countByNombre(@RequestParam String nombre) {
        Integer response = pasajeroService.countByNombre(nombre);
        DefaultApiResponse<Integer> apiResponse = new DefaultApiResponse<>(
          null,
          "Cantidad de pasajeros con el nombre: "+nombre,
          response,
          null
        );
        return ResponseEntity.ok(apiResponse);
    }

    @GetMapping("/por-numero-pasaporte")
    public ResponseEntity<DefaultApiResponse<PasajeroResponseDTO>> getByNumeroPasaporte (@RequestParam String numeroPasaporte) {
        PasajeroResponseDTO response = pasajeroService.findByPasaporteNumero(numeroPasaporte);
        DefaultApiResponse<PasajeroResponseDTO> apiResponse = new DefaultApiResponse<>(
          null,
          "Pasajero encontrado",
          response,
          null
        );
        return ResponseEntity.ok(apiResponse);
    }

    @GetMapping("/existe-alguno-con-pasaporte")
    public ResponseEntity<DefaultApiResponse<Boolean>> existsWithPasaporte(){
        Boolean response = pasajeroService.existsWithPasaporte();
        DefaultApiResponse<Boolean> apiResponse = new DefaultApiResponse<>(
          null,
          "Existe alguno con pasaporte",
          response,
                null
        );
        return ResponseEntity.ok(apiResponse);
    }

    @DeleteMapping("/borrar-por-nid")
    public ResponseEntity<DefaultApiResponse<Boolean>> deletePasajeroByNid(@RequestParam String nid) {
        Boolean response = pasajeroService.deleteByNId(nid);
        DefaultApiResponse<Boolean> apiResponse = new DefaultApiResponse<>(
          null,
          "Pasajero eliminado",
                response,
                null
        );
        return ResponseEntity.ok(apiResponse);
    }

}
