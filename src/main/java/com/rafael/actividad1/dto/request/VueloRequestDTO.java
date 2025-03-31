package com.rafael.actividad1.dto.request;

import jakarta.validation.constraints.NotNull;

import java.util.List;
import java.util.UUID;

public record VueloRequestDTO(@NotNull UUID numeroVuelo, @NotNull String origen, @NotNull String destino,
                              List<AerolineaRequestDTO> aerolineas, List<ReservaRequestDTO> reservas) {
}
