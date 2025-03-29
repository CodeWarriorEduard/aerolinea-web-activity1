package com.rafael.actividad1.dto.request;

import com.rafael.actividad1.entity.Vuelo;

import java.util.List;

public record AerolineaRequestDTO(String nombre, List<Vuelo> vuelos) {
}
