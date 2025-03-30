package com.rafael.actividad1.service;

import com.rafael.actividad1.dto.request.VueloRequestDTO;
import com.rafael.actividad1.dto.response.VueloResponseDTO;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.UUID;

public interface VueloService {
    VueloResponseDTO findById(Long id);

    List<VueloResponseDTO> findVuelosByDestino(String name);

    VueloResponseDTO save(VueloRequestDTO vueloRequestDTO);

    List<VueloResponseDTO> findAll();

    Long countVuelosByDestino(String destino);

    @Query("select v from Vuelo v where v.origen = :origen")
    List<VueloResponseDTO> findVuelosByOrigen(String origen);

    @Query("select  v from Vuelo  v order by v.destino")
    List<VueloResponseDTO> vuelosOrderedByDestino();

    @Query("select v from Vuelo v where v.numeroVuelo = :numVuelo")
    VueloResponseDTO findVueloByNumeroVuelo(UUID numVuelo);


    @Query("select v from Vuelo v where v.destino like concat(:letra, '%') ")
    List<VueloResponseDTO> findVuelosByDestinoStartsWith(String letra);

    @Query("select count(v) from Vuelo  v join v.aerolineas a where  a.nombre = :nombre")
    Long numberOfVueloByNameOfAerolinea(String nombre);
}
