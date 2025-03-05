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

    @Query("select aerolinea from Aerolinea aerolinea where aerolinea.nombre like %a")
    List<Aerolinea> aerolineaStartsWithA();

    @Query("select aerolinea from Aerolinea join vuelos_aerolineas v on v.aerolinea_id = aerolinea.id join Reserva r on r.vuelo.id = v.vuelo_id join Pasajero  p on p.id = r.pasajero.id where p.nombre == :nombre")
    Optional<Aerolinea> findAerolineaByPassengerName(String name);

    @Query("select count(aerolineas.id) from Aerolinea aerolineas join vuelos_aerolineas va on va.aerolinea_id = aerolineas.id group by aerolineas")
    List<Aerolinea> aerolineasTwoVuelos();



}
