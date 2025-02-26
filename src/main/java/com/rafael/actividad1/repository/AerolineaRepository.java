package com.rafael.actividad1.repository;

import com.rafael.actividad1.entity.Aerolinea;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AerolineaRepository extends JpaRepository<Aerolinea, Long> {
}
