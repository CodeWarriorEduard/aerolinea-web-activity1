package com.rafael.actividad1.repository;

import com.rafael.actividad1.entity.Aerolinea;
import com.rafael.actividad1.entity.Vuelo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface VueloRepository extends JpaRepository<Vuelo, Long>{


    Optional<Vuelo> findById(Long id);

    List<Vuelo> findVuelosByDestino(String name);

    Vuelo save(Vuelo vuelo);

    List<Vuelo> findAll();

    Long countVuelosByDestino(String destino);

    @Query("select v from Vuelo v where v.origen = :origen")
    List<Vuelo> findVueloByOrigen(String origen);

    @Query("select  v from Vuelo  v order by v.destino")
    List<Vuelo> vuelosOrderedByDestino();

    @Query("select v from Vuelo v where v.numeroVuelo = :numVuelo")
    Optional<Vuelo> findVueloByNumeroVuelo(UUID numVuelo);


    @Query("select v from Vuelo v where v.destino like concat(:letra, '%') ")
    List<Vuelo> findVueloByDestinoStartsWith(String letra);

    @Query("select count(v) from Vuelo  v join v.aerolineas a where  a.nombre = :nombre")
    Long numberOfVueloByNameOfAerolinea(String nombre);

}
