package com.rafael.actividad1.service;

import com.rafael.actividad1.dto.response.PasajeroResponseDTO;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface PasajeroService {
    List<PasajeroResponseDTO> findByNombre(String nombre);
    PasajeroResponseDTO findByNid(String nId);
    List<PasajeroResponseDTO> findByNombreContaining(String partialName);
    boolean existsByNid(String nId);
    int countByNombre(String nombre);
    PasajeroResponseDTO findByPasaporteNumero(String passportNumber);
    List<PasajeroResponseDTO> searchByPartialName(String partialName);
    boolean existsWithPasaporte();
    PasajeroResponseDTO findByNidAndNombre(@Param("nid") String nid, @Param("name") String name);
    boolean deleteByNId(String nId);
}
