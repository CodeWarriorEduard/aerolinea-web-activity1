package com.rafael.actividad1.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.util.List;
import java.util.UUID;

public record VueloRequestDTO(@NotNull UUID numeroVuelo, @NotBlank String origen, @NotBlank String destino,
                              List<AerolineaRequestDTO> aerolineas, List<ReservaRequestDTO> reservas) {
}
