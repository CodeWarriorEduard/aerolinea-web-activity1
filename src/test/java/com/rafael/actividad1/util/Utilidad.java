package com.rafael.actividad1.util;

import com.rafael.actividad1.entity.Pasajero;
import com.rafael.actividad1.entity.Pasaporte;
import com.rafael.actividad1.entity.Reserva;
import com.rafael.actividad1.entity.Vuelo;
import com.rafael.actividad1.repository.PasajeroRepository;
import com.rafael.actividad1.repository.PasaporteRepository;

import java.util.UUID;

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

    public static Pasaporte crearPasaporte(String numero){
        return Pasaporte.builder().numero(numero).build();
    }

    public static Pasaporte crearPasaporteConPasajero(String numero,Pasajero pasajero){
        return Pasaporte.builder().numero(numero).pasajero(pasajero).build();
    }


    public static Vuelo crearVuelo(String origen, String destino){
        return Vuelo.builder()
                .numeroVuelo(UUID.randomUUID())
                .origen(origen)
                .destino(destino)
                .build();
    }

    public static Reserva crearReserva(Pasajero pasajero,Vuelo vuelo){
        return Reserva.builder()
                .codigoReserva(UUID.randomUUID())
                .pasajero(pasajero)
                .vuelo(vuelo)
                .build();
    }
}
