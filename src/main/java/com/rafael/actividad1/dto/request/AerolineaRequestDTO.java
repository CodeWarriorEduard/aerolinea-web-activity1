package com.rafael.actividad1.dto.request;

import com.rafael.actividad1.entity.Vuelo;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;

import java.util.List;


public record AerolineaRequestDTO(@NotNull String nombre, List<Vuelo> vuelos) {
}
