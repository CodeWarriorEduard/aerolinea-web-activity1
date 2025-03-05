package com.rafael.actividad1.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name = "pasaportes")
public class Pasaporte {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column(nullable = false)
    private String numero;

    @OneToOne
    @JoinColumn(name = "pasajero_id", referencedColumnName = "id", unique = true)
    private Pasajero pasajero;

}
