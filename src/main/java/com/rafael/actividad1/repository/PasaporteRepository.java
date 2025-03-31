package com.rafael.actividad1.repository;

import com.rafael.actividad1.entity.Pasaporte;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface PasaporteRepository extends JpaRepository<Pasaporte, Long> {

    Optional<Pasaporte> findByNumero(String numero);

    Optional<Pasaporte> findByPasajeroId(Long pasajeroId);

    boolean existsByNumero(String numero);

    List<Pasaporte> findByPasajeroNombre(String nombre);

    int deleteByNumero(String numero);

    @Query("select p from Pasaporte p where p.pasajero.nid =:nId")
    Optional<Pasaporte> findByPasajeroNId(String nId);

    @Query("select p from Pasaporte p where p.numero like %:partialNumber%")
    List<Pasaporte> searchByPartialNumber(String partialNumber);

    @Query("select p from Pasaporte p where p.pasajero.nombre =:name and p.pasajero.nid =:nid")
    Optional<Pasaporte> findByPasajeroNombreAndNid(String name, String nid);

    @Query("select count(p) > 0 from Pasaporte p where p.pasajero.nid =:passengerNid")
    boolean existsByPasajeroNid(String passengerNid);

    @Modifying
    @Transactional
    @Query("delete from Pasaporte p where p.pasajero.nid =:passengerNid")
    int deleteByPasajeroNid(String passengerNid);
}
