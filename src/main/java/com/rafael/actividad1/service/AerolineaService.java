package com.rafael.actividad1.service;

import com.rafael.actividad1.dto.request.AerolineaRequestDTO;
import com.rafael.actividad1.dto.response.AerolineaResponseDTO;
import com.rafael.actividad1.dto.response.AerolineaVuelosResponseDTO;
import com.rafael.actividad1.entity.Aerolinea;

import java.util.List;
import java.util.Optional;

public interface AerolineaService {

    boolean deleteAerolineaById(Long id);
    AerolineaResponseDTO findAerolineaById(Long id);
    AerolineaResponseDTO saveAerolinea(Aerolinea aerolinea);
    List<AerolineaResponseDTO> findAllAerolineasInDb();
    Optional<AerolineaResponseDTO> findAerolineaByNombre(String nombre);
    List<AerolineaResponseDTO> aerolineaStartsWithA();
    List<AerolineaResponseDTO> findAerolineaByPassengerName(String nombre);
    List<AerolineaVuelosResponseDTO> aerolineasWithTwoFlightsx();
    List<AerolineaResponseDTO> findAllOrderedByName();
    Long countAllAerolineas();
}
