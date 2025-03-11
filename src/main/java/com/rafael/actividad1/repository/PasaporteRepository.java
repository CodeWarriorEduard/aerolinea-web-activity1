package com.rafael.actividad1.repository;

import com.rafael.actividad1.entity.Pasaporte;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface PasaporteRepository extends JpaRepository<Pasaporte, Long> {

    List<Pasaporte> findByNumero(String numero);

    Optional<Pasaporte> findByPasajeroId(Long pasajeroId);

    boolean existsByNumero(String numero);

    Optional<Pasaporte> findByPasajeroNombre(String nombre);

    void deleteByNumero(String numero);

    @Query("select p from Pasaporte p where p.pasajero.nid =: nId")
    Optional<Pasaporte> findByPasajeroNId(String nId);

    @Query("select p from Pasaporte p where p.numero like %:partialNumber%")
    List<Pasaporte> searchByPartialNumber(String partialNumber);

    @Query("select p from Pasaporte p where p.pasajero.nombre =: name and p.pasajero.id =: id")
    Optional<Pasaporte> findByPasajeroNombreAndId(String name, Long id);

    @Query("select count(p) > 0 from Pasaporte p where p.pasajero.id =: passengerId")
    boolean existsByPasajeroId(Long passengerId);

    @Query("delete from Pasaporte p where p.pasajero.id =: passengerId")
    void deleteByPasajeroId(Long passengerId);
}
