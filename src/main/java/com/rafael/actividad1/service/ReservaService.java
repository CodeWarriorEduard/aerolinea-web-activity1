package com.rafael.actividad1.service;

import com.rafael.actividad1.dto.response.ReservaResponseDTO;

import java.util.List;
import java.util.UUID;

public interface ReservaService {

    ReservaResponseDTO findByCodigoReserva(UUID codigoReserva);

    List<ReservaResponseDTO> findByVueloId(Long vueloId);

    boolean existsByCodigoReserva(UUID codigoReserva);

    Long countByVueloId(Long vueloId);

    List<ReservaResponseDTO> findByPasajeroNid(String nId);

    ReservaResponseDTO findByFlightAndPassenger(Long flightId, Long passengerId);

    List<ReservaResponseDTO> findByPasajeroNombre(String passengerName);

    boolean existsByFlightId(Long flightId);

    int deleteByCodigo(UUID code);
}
