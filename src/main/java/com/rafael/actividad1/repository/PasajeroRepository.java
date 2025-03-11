package com.rafael.actividad1.repository;

import com.rafael.actividad1.entity.Aerolinea;
import com.rafael.actividad1.entity.Pasajero;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface PasajeroRepository extends JpaRepository<Pasajero, Long> {

    Optional<Pasajero> findByNombre(String nombre);

    Optional<Pasajero> findByNid(String nId);

    List<Pasajero> findByNombreContaining(String partialName);

    boolean existsByNid(String nId);

    int countByNombre(String nombre);

    @Query("select p from Pasajero p where p.pasaporte.numero =: passportNumber")
    Optional<Pasajero> findByPasaporteNumero(String passportNumber);

    @Query("select p from Pasajero p where p.nombre like %:partialName%")
    List<Pasajero> searchByPartialName(String partialName);

    @Query("select count(p) > 0 from Pasajero p where p.pasaporte.id is not null")
    boolean existsWithPasaporte();

    @Query("select p from Pasajero p where p.nid =:nid and p.nombre =:name")
    Optional<Pasajero> findByNidAndNombre(@Param("nid") String nid,@Param("name") String name);

    @Modifying
    @Transactional
    @Query("delete from Pasajero p where p.nid =:nId")
    void deleteByNId(String nId);
}
