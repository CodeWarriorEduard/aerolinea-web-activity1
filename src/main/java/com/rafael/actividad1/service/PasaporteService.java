package com.rafael.actividad1.service;

import com.rafael.actividad1.dto.response.PasaporteResponseDTO;

import java.util.List;

public interface PasaporteService {

    PasaporteResponseDTO findByNumero(String numero);

    PasaporteResponseDTO findByPasajeroId(Long pasajeroId);

    boolean existsByNumero(String numero);

    PasaporteResponseDTO findByPasajeroNombre(String nombre);

    int deleteByNumero(String numero);

    PasaporteResponseDTO findByPasajeroNId(String nId);

    List<PasaporteResponseDTO> searchByPartialNumber(String partialNumber);

    PasaporteResponseDTO findByPasajeroNombreAndNid(String name, String nid);

    boolean existsByPasajeroNid(String passengerNid);

    int deleteByPasajeroNid(String passengerNid);
}
