package com.rafael.actividad1.repository;

import com.rafael.actividad1.entity.Aerolinea;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface AerolineaRepository extends JpaRepository<Aerolinea, Long> {

    void deleteById(Long id);
    Optional<Aerolinea> findById(Long id);
    Aerolinea save(Aerolinea aerolinea);
    List<Aerolinea> findAll();
    Optional<Aerolinea> findByNombre(String nombre);


    @Query("select aerolinea from Aerolinea aerolinea where aerolinea.nombre like concat('A', '%')")
    List<Aerolinea> aerolineaStartsWithA();

    @Query("select distinct  aerolineas from Aerolinea  aerolineas join  aerolineas.vuelos v join v.reservas r join r.pasajero p where p.nombre = :nombre")
    List<Aerolinea> findAerolineaByPassengerName(String nombre);

    @Query("select aerolineas from Aerolinea aerolineas join aerolineas.vuelos v group by aerolineas having count(*) = 2")
    List<Aerolinea> aerolineasWithTwoFlightsx();

    @Query("select aerolineas from Aerolinea aerolineas order by  aerolineas.nombre")
    List<Aerolinea> findAllOrderedByName();

    @Query("select count(aerolineas) from Aerolinea  aerolineas")
    Long countAllAerolineas();

}
