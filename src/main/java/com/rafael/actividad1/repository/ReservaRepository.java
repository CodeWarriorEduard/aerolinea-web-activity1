package com.rafael.actividad1.repository;

import com.rafael.actividad1.entity.Aerolinea;
import com.rafael.actividad1.entity.Reserva;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface ReservaRepository extends JpaRepository<Reserva, Long> {

    Optional<Reserva> findByCodigoReserva(UUID codigoReserva);

    List<Reserva> findByPasajeroId(Long pasajeroId);

    List<Reserva> findByVueloId(Long vueloId);

    boolean existsByCodigoReserva(UUID codigoReserva);

    int countByVueloId(Long vueloId);

    @Query("select r from Reserva r where r.pasajero.nid =: nId")
    List<Reserva> findByPasajeroNid(String nId);

    @Query("select r from Reserva r where r.vuelo.id = ?1 and r.pasajero.id =: passangerId")
    Optional<Reserva> findByFlightAndPassenger(Long flightId, Long passengerId);

    @Query("select r from Reserva r where r.pasajero.nombre =: passangerName")
    List<Reserva> findByPasajeroNombre(String passengerName);

    @Query("select count(r) > 0 from Reserva r where r.vuelo.id = ?1")
    boolean existsByFlightId(Long flightId);

    @Query("delete from Reserva r where r.codigoReserva =: code")
    void deleteByCodigo(UUID code);
}
