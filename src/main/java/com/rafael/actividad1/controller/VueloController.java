package com.rafael.actividad1.controller;

import com.rafael.actividad1.dto.request.VueloRequestDTO;
import com.rafael.actividad1.dto.response.VueloResponseDTO;
import com.rafael.actividad1.response.DefaultApiResponse;
import com.rafael.actividad1.service.VueloService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("api/v1/vuelos")
public class VueloController {

    private final VueloService vueloService;

    @Autowired
    public VueloController(VueloService vueloService) {
        this.vueloService = vueloService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<DefaultApiResponse<VueloResponseDTO>> getVueloById(@PathVariable Long id) {

        VueloResponseDTO vuelo = vueloService.findById(id);

        DefaultApiResponse<VueloResponseDTO> apiResponse = new DefaultApiResponse<>(
                null,
                "Vuelo encontrado",
                vuelo,
                null
        );

        return ResponseEntity.ok(apiResponse);
    }

    @GetMapping("/por-destino")
    public ResponseEntity<DefaultApiResponse<List<VueloResponseDTO>>> getVuelosByDestino
            (@RequestParam String destino) {
        List<VueloResponseDTO> response = vueloService.findVuelosByDestino(destino);

        DefaultApiResponse<List<VueloResponseDTO>> apiResponse = new DefaultApiResponse<>(
                null,
                "Vuelos con el destino: " + destino,
                response,
                null
        );

        return response.isEmpty() ? ResponseEntity.noContent().build() : ResponseEntity.ok(apiResponse);
    }

    @PostMapping("/guardar")
    public ResponseEntity<DefaultApiResponse<VueloResponseDTO>> saveVuelo
            (@RequestBody VueloRequestDTO vueloRequestDTO) {
        VueloResponseDTO vueloResponse = vueloService.save(vueloRequestDTO);

        DefaultApiResponse<VueloResponseDTO> apiResponse = new DefaultApiResponse<>(
                null,
                "Vuelo creado",
                vueloResponse,
                null
        );

        return ResponseEntity.ok(apiResponse);
    }

    @GetMapping("/todos")
    public ResponseEntity<DefaultApiResponse<List<VueloResponseDTO>>> getVuelos() {
        List<VueloResponseDTO> response = vueloService.findAll();

        DefaultApiResponse<List<VueloResponseDTO>> apiResponse = new DefaultApiResponse<>(
                null,
                "Vuelos",
                response,
                null
        );

        return ResponseEntity.ok(apiResponse);
    }

    @GetMapping("/cuenta-por-destino")
    public ResponseEntity<DefaultApiResponse<Long>> getCountVuelosPorDestino(@RequestParam String destino) {
        Long response = vueloService.countVuelosByDestino(destino);

        DefaultApiResponse<Long> apiResponse = new DefaultApiResponse<>(
                null,
                "Numero de vuelos con el destino: " + destino,
                response,
                null
        );

        return ResponseEntity.ok(apiResponse);
    }

    @GetMapping("/por-origen")
    public ResponseEntity<DefaultApiResponse<List<VueloResponseDTO>>> getVuelosByOrigen
            (@RequestParam String origen) {
        List<VueloResponseDTO> response = vueloService.findVuelosByOrigen(origen);

        DefaultApiResponse<List<VueloResponseDTO>> apiResponse = new DefaultApiResponse<>(
                null,
                "Vuelos con el origen: " + origen,
                response,
                null
        );

        return response.isEmpty() ? ResponseEntity.noContent().build() : ResponseEntity.ok(apiResponse);
    }

    @GetMapping("/ordenados-por-destino")
    public ResponseEntity<DefaultApiResponse<List<VueloResponseDTO>>> getVuelosOrderedByDestino
            () {
        List<VueloResponseDTO> response = vueloService.vuelosOrderedByDestino();

        DefaultApiResponse<List<VueloResponseDTO>> apiResponse = new DefaultApiResponse<>(
                null,
                "Vuelos ordenados por destino",
                response,
                null
        );

        return response.isEmpty() ? ResponseEntity.noContent().build() : ResponseEntity.ok(apiResponse);
    }

    @GetMapping("por-numero-vuelo")
    public ResponseEntity<DefaultApiResponse<VueloResponseDTO>> getVuelosPorNumeroVuelo
            (@RequestParam String numeroVuelo) {

        VueloResponseDTO vueloResponseDTO = vueloService.findVueloByNumeroVuelo(UUID.fromString(numeroVuelo));
        DefaultApiResponse<VueloResponseDTO> apiResponse = new DefaultApiResponse<>(
                null,
                "Vuelo encontrado",
                vueloResponseDTO,
                null
        );

        return ResponseEntity.ok(apiResponse);
    }

    @GetMapping("destino-inicia-por-letra")
    public ResponseEntity<DefaultApiResponse<List<VueloResponseDTO>>> getVuelosPorLetra
            (@RequestParam String letra) {
        List<VueloResponseDTO> response = vueloService.findVuelosByDestinoStartsWith(letra);

        DefaultApiResponse<List<VueloResponseDTO>> apiResponse = new DefaultApiResponse<>(
                null,
                "Vuelos encontrados",
                response,
                null
        );

        return response.isEmpty() ? ResponseEntity.noContent().build() : ResponseEntity.ok(apiResponse);
    }

    @GetMapping("cuenta-por-nombre-aerolinea")
    public ResponseEntity<DefaultApiResponse<Long>> getCountVuelosPorNombreAerolinea
            (@RequestParam String nombreAerolinea) {
        Long response = vueloService.numberOfVueloByNameOfAerolinea(nombreAerolinea);
        DefaultApiResponse<Long> apiResponse = new DefaultApiResponse<>(
          null,
          "Numero de vuelos de la aerolinea: "+nombreAerolinea,
          response,
          null
        );
        return ResponseEntity.ok(apiResponse);
    }
}
