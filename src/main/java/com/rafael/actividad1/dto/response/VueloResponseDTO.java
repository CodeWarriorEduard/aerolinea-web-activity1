package com.rafael.actividad1.dto.response;

import java.util.UUID;

public record VueloResponseDTO(UUID numeroVuelo, String origen, String destino) {
}
