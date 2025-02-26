package com.rafael.actividad1.repository;

import com.rafael.actividad1.entity.Aerolinea;
import com.rafael.actividad1.entity.Pasaporte;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PasaporteRepository extends JpaRepository<Pasaporte, Long> {
}
