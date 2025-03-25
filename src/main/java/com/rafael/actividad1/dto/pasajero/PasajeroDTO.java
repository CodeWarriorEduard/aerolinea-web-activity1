package com.rafael.actividad1.dto.pasajero;

import com.rafael.actividad1.dto.pasaporte.PasaporteDTO;

public record PasajeroDTO(String nombre, String nid, PasaporteDTO pasaporte) {
}
