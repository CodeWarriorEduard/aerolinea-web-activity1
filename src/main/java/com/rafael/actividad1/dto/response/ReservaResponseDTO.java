package com.rafael.actividad1.dto.response;

import java.util.UUID;

public record ReservaResponseDTO(UUID codigoReserva, PasajeroResponseDTO pasajero, VueloResponseDTO vuelo) {
}
