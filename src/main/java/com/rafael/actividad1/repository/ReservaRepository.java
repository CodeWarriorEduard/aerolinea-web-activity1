package com.rafael.actividad1.repository;

import com.rafael.actividad1.entity.Aerolinea;
import com.rafael.actividad1.entity.Reserva;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReservaRepository extends JpaRepository<Reserva, Long> {
}
