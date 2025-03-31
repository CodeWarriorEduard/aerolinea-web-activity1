package com.rafael.actividad1.repository;

import com.rafael.actividad1.config.TestContainerConfig;
import com.rafael.actividad1.entity.Pasajero;
import com.rafael.actividad1.entity.Pasaporte;
import com.rafael.actividad1.util.Utilidad;
import jakarta.persistence.EntityManager;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;
import org.testcontainers.junit.jupiter.Testcontainers;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@Import(TestContainerConfig.class)
@Testcontainers
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class PasaporteRepositoryTest {

    @Autowired
    PasaporteRepository pasaporteRepository;

    @Autowired
    PasajeroRepository pasajeroRepository;

    @Autowired
    EntityManager em;

    @Test
    void whenFindingPasaporteByNumero_thenItReturnsCorrectPasaporte() {
        Pasaporte pasaporte = pasaporteRepository.save(Utilidad.crearPasaporte("1a"));
        assertTrue(pasaporteRepository.findByNumero("1a").isPresent());
        assertEquals(pasaporte.getId(),pasaporteRepository.findByNumero("1a").get().getId());
    }

    @Test
    void whenFindingPasaporteByPasajeroId_thenItReturnsCorrectPasaporte() {
        Pasajero pasajero = pasajeroRepository.save(Utilidad.crearPasajero("polloT", "123abc"));

        Pasaporte pasaporte = pasaporteRepository.save(Utilidad.crearPasaporteConPasajero("A123", pasajero));

        Optional<Pasaporte> encontrado = pasaporteRepository.findByPasajeroId(pasajero.getId());

        assertTrue(encontrado.isPresent(), "El pasaporte debería estar presente");
        assertEquals(pasaporte.getId(), encontrado.get().getId(), "El ID del pasaporte no coincide");

    }

    @Test
    void whenPasaporteWithNumeroExists_thenItReturnsTrue() {
        pasaporteRepository.save(Utilidad.crearPasaporte("1a"));
        assertTrue(pasaporteRepository.existsByNumero("1a"));
    }

    @Test
    void whenPasaporteWithPasajeroNombreExists_thenItReturnTheCorrectPasaporte() {
        Pasajero pasajero = pasajeroRepository.save(Utilidad.crearPasajero("polloT", "123abc"));

        Pasaporte pasaporte = pasaporteRepository.save(Utilidad.crearPasaporteConPasajero("A123", pasajero));

        assertEquals("A123",pasaporteRepository.findByPasajeroNombre("polloT").get(0).getNumero());
    }

    @Test
    void whenPasaporteIsDeletedByNumero_thenItsDeleted() {
        Pasaporte pasaporte = pasaporteRepository.save(Utilidad.crearPasaporte("1a"));
        assertTrue(pasaporteRepository.findById(pasaporte.getId()).isPresent());
        pasaporteRepository.deleteByNumero("1a");
        assertFalse(pasaporteRepository.findById(pasaporte.getId()).isPresent());
    }

    @Test
    void whenFindingPasaporteByPasajeroNid_thenRerturnsCorrectPasaporte() {
        Pasajero pasajero = pasajeroRepository.save(Utilidad.crearPasajero("polloT", "123abc"));

        Pasaporte pasaporte = pasaporteRepository.save(Utilidad.crearPasaporteConPasajero("A123", pasajero));

        assertEquals("A123",pasaporteRepository.findByPasajeroNId("123abc").get().getNumero());
    }

    @Test
    void whenSearchPasaportesByPartialNumber_thenItReturnsCorrectPasaportes() {
        pasaporteRepository.save(Utilidad.crearPasaporte("1a"));
        pasaporteRepository.save(Utilidad.crearPasaporte("1b"));
        pasaporteRepository.save(Utilidad.crearPasaporte("2a"));

        assertTrue(pasaporteRepository.searchByPartialNumber("1").size() == 2);
    }

    @Test
    void whenFindingPasaporteByPasajeroNombreAndId_thenItReturnsCorrectPasaporte() {
        Pasajero pasajero = pasajeroRepository.save(Utilidad.crearPasajero("polloT", "123abc"));

        Pasaporte pasaporte = pasaporteRepository.save(Utilidad.crearPasaporteConPasajero("A123", pasajero));

        assertEquals("A123",pasaporteRepository.findByPasajeroNombreAndNid("polloT","123abc").get().getNumero());
    }

    @Test
    void whenPasaporteWithPasajeroNidExists_thenItsFounded() {
        Pasajero pasajero = pasajeroRepository.save(Utilidad.crearPasajero("polloT", "123abc"));

        Pasaporte pasaporte = pasaporteRepository.save(Utilidad.crearPasaporteConPasajero("A123", pasajero));

        assertTrue(pasaporteRepository.existsByPasajeroNid("123abc"));
    }

    @Test
    void whenPasaporteIsDeleteByPasajeroNid_thenItsDeleted() {
        Pasajero pasajero = pasajeroRepository.save(Utilidad.crearPasajero("polloT", "123abc"));

        Pasaporte pasaporte = pasaporteRepository.save(Utilidad.crearPasaporteConPasajero("A123", pasajero));

        assertTrue(pasaporteRepository.existsById(pasaporte.getId()));
        pasaporteRepository.deleteByPasajeroNid(pasajero.getNid());

        long count = pasaporteRepository.count();
        System.out.println("Cantidad de pasaportes después de eliminar: " + count);
        em.clear();
        pasaporteRepository.flush();
        assertFalse(pasaporteRepository.findById(pasaporte.getId()).isPresent());

    }
}