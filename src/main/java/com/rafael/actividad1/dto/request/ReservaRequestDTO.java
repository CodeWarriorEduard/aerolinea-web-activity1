package com.rafael.actividad1.dto.request;

import java.util.UUID;

public record ReservaRequestDTO(UUID codigoReserva, PasajeroRequestDTO pasajero, VueloRequestDTO vuelo) {
}
