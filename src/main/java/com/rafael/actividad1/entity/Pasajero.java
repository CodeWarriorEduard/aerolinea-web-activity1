package com.rafael.actividad1.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

import java.util.List;

@Entity
@Getter
@Setter
@Table(name = "pasajeros")
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Pasajero {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column(nullable = false)
    @NotBlank(message = "El nombre es un campo obligatorio >:(")
    private String nombre;
    @Column(nullable = false)
    @NotBlank(message = "El numero de identificacion es un campo obligatorio")
    private String nid;

    @OneToOne(mappedBy = "pasajero")
    private Pasaporte pasaporte;

    @OneToMany(mappedBy = "pasajero")
    private List<Reserva> reservas;
}
