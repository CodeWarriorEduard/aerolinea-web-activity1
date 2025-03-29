package com.rafael.actividad1.service;

import com.rafael.actividad1.dto.request.PasajeroRequestDTO;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface PasajeroService {
    PasajeroRequestDTO findByNombre(String nombre);
    PasajeroRequestDTO findByNid(String nId);
    List<PasajeroRequestDTO> findByNombreContaining(String partialName);
    boolean existsByNid(String nId);
    int countByNombre(String nombre);
    PasajeroRequestDTO findByPasaporteNumero(String passportNumber);
    List<PasajeroRequestDTO> searchByPartialName(String partialName);
    boolean existsWithPasaporte();
    PasajeroRequestDTO findByNidAndNombre(@Param("nid") String nid, @Param("name") String name);
    boolean deleteByNId(String nId);
}
