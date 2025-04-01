package com.rafael.actividad1.dto.response;

import java.util.List;

public record AerolineaVuelosResponseDTO(String nombre, List<VueloResponseDTO> vuelos) {
}
