package com.rafael.actividad1.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;
import java.util.UUID;

@Entity
@Data
@Table(name = "reservas")
public class Reserva {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column(nullable = false)
    private UUID codigoReserva;

   @ManyToOne
   @JoinColumn(name = "pasajero_id", referencedColumnName = "id")
   private Pasajero pasajero;

    @ManyToOne
    @JoinColumn(name = "vuelo_id", referencedColumnName = "id")
    private Vuelo vuelo;

}
