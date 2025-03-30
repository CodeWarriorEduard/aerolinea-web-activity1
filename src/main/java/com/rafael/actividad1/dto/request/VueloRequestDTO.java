package com.rafael.actividad1.dto.request;

import java.util.List;
import java.util.UUID;

public record VueloRequestDTO(UUID numeroVuelo, String origen, String destino,
                              List<AerolineaRequestDTO> aerolineas, List<ReservaRequestDTO> reservas) {
}
