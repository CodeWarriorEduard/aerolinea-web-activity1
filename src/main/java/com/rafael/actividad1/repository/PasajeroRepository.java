package com.rafael.actividad1.repository;

import com.rafael.actividad1.entity.Aerolinea;
import com.rafael.actividad1.entity.Pasajero;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PasajeroRepository extends JpaRepository<Pasajero, Long> {
}
