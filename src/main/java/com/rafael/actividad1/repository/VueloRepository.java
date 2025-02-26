package com.rafael.actividad1.repository;

import com.rafael.actividad1.entity.Aerolinea;
import com.rafael.actividad1.entity.Vuelo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VueloRepository extends JpaRepository<Vuelo, Long> {
}
