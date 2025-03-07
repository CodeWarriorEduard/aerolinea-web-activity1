package com.rafael.actividad1.repository;

import com.rafael.actividad1.entity.Aerolinea;
import com.rafael.actividad1.entity.Pasajero;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface PasajeroRepository extends JpaRepository<Pasajero, Long> {

    Optional<Pasajero> findByNombre(String nombre);

    Optional<Pasajero> findByNId(String nId);

    List<Pasajero> findByNombreContaining(String partialName);

    boolean existsByNId(String nId);

    int countByNombre(String nombre);

    @Query("select p from Pasajero p where p.pasaporte.numero =: passportNumber")
    Optional<Pasajero> findByPasaporteNumero(String passportNumber);

    @Query("select p from Pasajero p where p.nombre like %:partialName%")
    List<Pasajero> searchByPartialName(String partialName);

    @Query("select count(p) > 0 from Pasajero p where p.pasaporte.id is not null")
    boolean existsWithPasaporte();

    @Query("select p from Pasajero p where p.nId =: nId and p.nombre =: name")
    Optional<Pasajero> findByNIdAndNombre(String nId, String name);

    @Query("delete from Pasajero p where p.nId =: nId")
    void deleteByNId(String nId);
}
