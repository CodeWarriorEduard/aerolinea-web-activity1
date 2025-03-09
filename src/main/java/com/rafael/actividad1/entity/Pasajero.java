package com.rafael.actividad1.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Entity
@Data
@Table(name = "pasajeros")
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Pasajero {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column(nullable = false)
    private String nombre;
    @Column(nullable = false)
    private String nId;

    @OneToOne(mappedBy = "pasajero")
    private Pasaporte pasaporte;

    @OneToMany(mappedBy = "pasajero")
    private List<Reserva> reserva;


}
