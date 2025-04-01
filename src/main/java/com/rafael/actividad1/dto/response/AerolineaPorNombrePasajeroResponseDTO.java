package com.rafael.actividad1.dto.response;

import java.util.List;

public record AerolineaPorNombrePasajeroResponseDTO(PasajeroResponseDTO pasajero, List<AerolineaResponseDTO> aerolinea) {
}
