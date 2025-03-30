package com.rafael.actividad1.service;

import com.rafael.actividad1.dto.response.VueloResponseDTO;
import com.rafael.actividad1.entity.Vuelo;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface VueloService {
    VueloResponseDTO findById(Long id);

    List<VueloResponseDTO> findVuelosByDestino(String name);

    VueloResponseDTO save(Vuelo vuelo);

    List<VueloResponseDTO> findAll();

    Long countVuelosByDestino(String destino);

    @Query("select v from Vuelo v where v.origen = :origen")
    List<VueloResponseDTO> findVueloByOrigen(String origen);

    @Query("select  v from Vuelo  v order by v.destino")
    List<VueloResponseDTO> vuelosOrderedByDestino();

    @Query("select v from Vuelo v where v.numeroVuelo = :numVuelo")
    VueloResponseDTO findVueloByNumeroVuelo(UUID numVuelo);


    @Query("select v from Vuelo v where v.destino like concat(:letra, '%') ")
    List<VueloResponseDTO> findVueloByDestinoStartsWith(String letra);

    @Query("select count(v) from Vuelo  v join v.aerolineas a where  a.nombre = :nombre")
    Long numberOfVueloByNameOfAerolinea(String nombre);
}
