package com.rafael.actividad1.util;

import com.rafael.actividad1.entity.Pasajero;
import com.rafael.actividad1.entity.Pasaporte;
import com.rafael.actividad1.repository.PasajeroRepository;
import com.rafael.actividad1.repository.PasaporteRepository;

public class Utilidad {

    public static Pasajero crearPasajero(String name, String nid){
        return Pasajero.builder()
                .nombre(name)
                .nid(nid)
                .build();
    }

    public static void crearPasajeroConPasaporte(PasajeroRepository pasajeroRepository, PasaporteRepository pasaporteRepository) {
        // Creamos un pasajero
        Pasajero pasajero = Pasajero.builder()
                .nombre("PasajeroT")
                .nid("123abc")
                .build();

        // Guardamos el pasajero en la base de datos
        pasajero = pasajeroRepository.save(pasajero);

        // Creamos un pasaporte y lo asociamos al pasajero
        Pasaporte pasaporte = Pasaporte.builder()
                .numero("xyz123")
                .pasajero(pasajero)
                .build();

        // Guardamos el pasaporte
        pasaporteRepository.save(pasaporte);
    }
}
