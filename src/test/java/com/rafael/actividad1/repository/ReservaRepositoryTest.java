package com.rafael.actividad1.repository;

import com.rafael.actividad1.config.TestContainerConfig;
import com.rafael.actividad1.entity.Pasajero;
import com.rafael.actividad1.entity.Reserva;
import com.rafael.actividad1.entity.Vuelo;
import com.rafael.actividad1.util.Utilidad;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;
import org.testcontainers.junit.jupiter.Testcontainers;

import static org.junit.jupiter.api.Assertions.*;

@Import(TestContainerConfig.class)
@Testcontainers
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class ReservaRepositoryTest {

    @Autowired
    ReservaRepository reservaRepository;

    @Autowired
    PasajeroRepository pasajeroRepository;

    @Autowired
    VueloRepository vueloRepository;

    @Test
    void whenFindingReservaByCodigoReserva_thenItReturnsCorrectReserva() {

        Reserva reserva = reservaRepository.save(Utilidad.crearReserva(null,null));

        assertEquals(reserva.getId(), reservaRepository.findByCodigoReserva(reserva.getCodigoReserva()).get().getId());
    }

    @Test
    void whenFindingReservasByPasajeroId_thenItReturnsCorrectReservas() {

        Pasajero pasajero = pasajeroRepository.save(Utilidad.crearPasajero("polloT","123abc"));
        Reserva reserva = reservaRepository.save(Utilidad.crearReserva(pasajero,null));

        assertEquals(reserva.getId(), reservaRepository.findByPasajeroId(pasajero.getId()).get(0).getId());
    }

    @Test
    void whenFindingRerservasByVueloId_thenItReturnsCorrectRerservas() {
        Vuelo vuelo = vueloRepository.save(Utilidad.crearVuelo("origen1","destino1"));
        Reserva reserva = reservaRepository.save(Utilidad.crearReserva(null,vuelo));

        assertEquals(reserva.getId(), reservaRepository.findByVueloId(vuelo.getId()).get(0).getId());
    }

    @Test
    void whenExistsAReservaWithCodigoReserva_thenReturnsTrue() {
        Reserva reserva = reservaRepository.save(Utilidad.crearReserva(null,null));
        assertTrue(reservaRepository.existsByCodigoReserva(reserva.getCodigoReserva()));
    }

    @Test
    void whenCountingReservasByVueloId_thenReturnsCorrectCount() {
        Vuelo vuelo = vueloRepository.save(Utilidad.crearVuelo("a","b"));
        reservaRepository.save(Utilidad.crearReserva(null,vuelo));
        reservaRepository.save(Utilidad.crearReserva(null,vuelo));

        int count = reservaRepository.countByVueloId(vuelo.getId());

        assertEquals(2, count);
    }

    @Test
    void findByPasajeroNid() {
    }

    @Test
    void findByFlightAndPassenger() {
    }

    @Test
    void findByPasajeroNombre() {
    }

    @Test
    void existsByFlightId() {
    }

    @Test
    void deleteByCodigo() {
    }
}