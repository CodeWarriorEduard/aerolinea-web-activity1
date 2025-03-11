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
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.jdbc.Sql;
import org.testcontainers.junit.jupiter.Testcontainers;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

@Import(TestContainerConfig.class)
@Testcontainers
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Sql(scripts = "classpath:schema.sql")
class ReservaRepositoryTest {

    @Autowired
    private JdbcTemplate jdbcTemplate;

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
        String id = "P001";
        List<Reserva> reservas = reservaRepository.findByPasajeroNid(id);
        assertEquals(1, reservas.size());
    }

    @Test
    void findByFlightAndPassenger() {
        Long flight = 1L;
        Long passenger = 1L;

        Optional<Reserva> reserva = reservaRepository.findByFlightAndPassenger(flight, passenger);

        assertTrue(reserva.isPresent());
        assertEquals(UUID.fromString("550e8400-e29b-41d4-a716-446655440001"), reserva.get().getCodigoReserva());

    }

    @Test
    void findByPasajeroNombre() {

        String nombreExpected = "Juan Perez";

        List<String> nombre = List.of(
          "Juan Perez"
        );

        List<Reserva> reservas = reservaRepository.findByPasajeroNombre(nombreExpected);

        for (int i = 0; i < reservas.size(); i++) {
            assertEquals(nombre.get(i), reservas.get(i).getPasajero().getNombre());
        }

    }

    @Test
    void existsByFlightId() {
        Long flightId = 1L;
        boolean result = reservaRepository.existsByFlightId(flightId);
        assertTrue(result);
    }

    @Test
    void deleteByCodigo() {
        UUID uuid = UUID.fromString("550e8400-e29b-41d4-a716-446655440004");

        int result = reservaRepository.deleteByCodigo(uuid);

        assertEquals(1, result);

        // Verificamos que ya no exista la reserva

        Optional<Reserva> reserva = reservaRepository.findByCodigoReserva(uuid);

        assertTrue(reserva.isEmpty());

    }
}