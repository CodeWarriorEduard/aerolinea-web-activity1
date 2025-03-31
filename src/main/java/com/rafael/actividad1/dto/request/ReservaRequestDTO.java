package com.rafael.actividad1.dto.request;

import jakarta.validation.constraints.NotNull;

import java.util.UUID;

public record ReservaRequestDTO(@NotNull UUID codigoReserva, PasajeroRequestDTO pasajero, VueloRequestDTO vuelo) {
}
