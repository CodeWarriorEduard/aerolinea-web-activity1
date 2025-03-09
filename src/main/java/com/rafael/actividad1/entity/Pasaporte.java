package com.rafael.actividad1.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@Table(name = "pasaportes")
@AllArgsConstructor
@NoArgsConstructor
@Builder
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
