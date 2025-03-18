package com.rafael.actividad1.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Builder
@Entity
@Data
@Table(name = "aerolineas")
@AllArgsConstructor
@NoArgsConstructor
public class Aerolinea {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column(nullable = false, name = "nombre")
    private String nombre;

    @ManyToMany(mappedBy = "aerolineas")
    @JsonIgnore
    private List<Vuelo> vuelos;



}
