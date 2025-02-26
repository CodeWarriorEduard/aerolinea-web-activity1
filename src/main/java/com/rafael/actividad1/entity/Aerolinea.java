package com.rafael.actividad1.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Entity
@Data
@Table(name = "aerolineas")
public class Aerolinea {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column(nullable = false)
    private String nombre;

    @ManyToMany(mappedBy = "aerolineas")
    private List<Vuelo> vuelos;



}
