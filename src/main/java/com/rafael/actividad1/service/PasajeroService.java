package com.rafael.actividad1.service;

import com.rafael.actividad1.dto.pasajero.PasaporteDTO;
import com.rafael.actividad1.dto.pasaporte.PasajeroDTO;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface PasajeroService {
    PasajeroDTO findByNombre(String nombre);
    PasajeroDTO findByNid(String nId);
    List<PasajeroDTO> findByNombreContaining(String partialName);
    boolean existsByNid(String nId);
    int countByNombre(String nombre);
    PasajeroDTO findByPasaporteNumero(String passportNumber);
    List<PasajeroDTO> searchByPartialName(String partialName);
    boolean existsWithPasaporte();
    PasajeroDTO findByNidAndNombre(@Param("nid") String nid, @Param("name") String name);
    boolean deleteByNId(String nId);
}
