package com.rafael.actividad1.service;

import com.rafael.actividad1.entity.Aerolinea;

import java.util.List;
import java.util.Optional;

public interface AerolineaService {

    boolean deleteAerolineaById(Long id);
    Optional<Aerolinea> findAerolineaById(Long id);
    Aerolinea saveAerolinea(Aerolinea aerolinea);
    List<Aerolinea> findAllAerolineasInDb();
    Optional<Aerolinea> findAerolineaByNombre(String nombre);
    List<Aerolinea> aerolineaStartsWithA();
    List<Aerolinea> findAerolineaByPassengerName(String nombre);
    List<Aerolinea> aerolineasWithTwoFlightsx();
    List<Aerolinea> findAllOrderedByName();
    Long countAllAerolineas();
}
