package com.rafael.actividad1.repository;

import com.rafael.actividad1.entity.Reserva;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface ReservaRepository extends JpaRepository<Reserva, Long> {

    Optional<Reserva> findByCodigoReserva(UUID codigoReserva);

    List<Reserva> findByPasajeroId(Long pasajeroId);

    List<Reserva> findByVueloId(Long vueloId);

    boolean existsByCodigoReserva(UUID codigoReserva);

    Long countByVueloId(Long vueloId);

    @Query("select r from Reserva r join r.pasajero p where p.nid = :nId")
    List<Reserva> findByPasajeroNid(String nId);

    @Query("select r from Reserva r where r.vuelo.id = ?1 and r.pasajero.id = :passengerId")
    Optional<Reserva> findByFlightAndPassenger(Long flightId, Long passengerId);

    @Query("select r from Reserva r where r.pasajero.nombre =:passengerName")
    List<Reserva> findByPasajeroNombre(String passengerName);

    @Query("select count(r) > 0 from Reserva r where r.vuelo.id = ?1")
    boolean existsByFlightId(Long flightId);

    @Modifying
    @Transactional
    @Query("DELETE FROM Reserva r WHERE r.codigoReserva = :code")
    int deleteByCodigo(UUID code);

}
