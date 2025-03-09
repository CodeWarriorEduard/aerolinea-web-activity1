package com.rafael.actividad1.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.UUID;

@Entity
@Data
@Table(name = "vuelos")
@AllArgsConstructor
@NoArgsConstructor
@Builder

public class Vuelo {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column(nullable = false)
    private UUID numeroVuelo;
    @Column(nullable = false)
    private String origen;
    @Column(nullable = false)
    private String destino;

    @ManyToMany
    @JoinTable(
            name = "vuelos_aerolineas",
            joinColumns = @JoinColumn(name = "vuelo_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "aerolinea_id", referencedColumnName = "id")
    )
    private List<Aerolinea> aerolineas;

    @OneToMany(mappedBy = "vuelo")
    private List<Reserva> reservas;



}
