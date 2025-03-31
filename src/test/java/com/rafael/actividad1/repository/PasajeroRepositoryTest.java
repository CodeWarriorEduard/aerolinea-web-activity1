package com.rafael.actividad1.repository;

import com.rafael.actividad1.config.TestContainerConfig;
import com.rafael.actividad1.entity.Pasajero;
import com.rafael.actividad1.entity.Pasaporte;
import com.rafael.actividad1.util.Utilidad;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.test.annotation.DirtiesContext;
import org.testcontainers.junit.jupiter.Testcontainers;

import static org.junit.jupiter.api.Assertions.*;

@Import(TestContainerConfig.class)
@Testcontainers
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class PasajeroRepositoryTest {

//    @Container
//    public static PostgreSQLContainer<?> postgres = new PostgreSQLContainer<>("postgres:latest")
//            .withDatabaseName("testdb")
//            .withUsername("testuser")
//            .withPassword("testpass");

    @Autowired
    PasajeroRepository pasajeroRepository;

    @Autowired
    PasaporteRepository pasaporteRepository;


    @Test
    void whenSavingPasajero_thenItIsPersistedCorrectly(){
        Pasajero pasajero = Pasajero.builder()
                .nombre("pasajeroT")
                .nid("123abc")
                .build();

        Pasajero savedPasajero = pasajeroRepository.save(pasajero);
        assertEquals(pasajero.getNid(), savedPasajero.getNid());
    }

    @Test
    void whenFindingPasajeroByName_thenItReturnsCorrectPasajero(){
        Pasajero pasajero = pasajeroRepository.save(Utilidad.crearPasajero("pasajeroT", "123abc"));
        assertTrue(pasajeroRepository.findById(pasajero.getId()).isPresent());

        assertEquals("123abc", pasajeroRepository.findByNombre("pasajeroT").get(0).getNid());
    }

    @Test
    void whenFindingPasajeroByNid_thenItReturnsCorrectPasajero(){
        Pasajero pasajero = pasajeroRepository.save(Utilidad.crearPasajero("pasajeroT", "123abc"));
        assertTrue(pasajeroRepository.findById(pasajero.getId()).isPresent());
        assertEquals(pasajero.getId(), pasajeroRepository.findByNid("123abc").get().getId());
    }

    @Test
    void whenFindingPasajerosByPartialName_thenItReturnsMatchingPasajeros(){
        pasajeroRepository.save(Utilidad.crearPasajero("pasajeroT", "123abc"));
        pasajeroRepository.save(Utilidad.crearPasajero("polloTA", "122abc"));
        pasajeroRepository.save(Utilidad.crearPasajero("polloTB", "133abc"));

        assertTrue(!pasajeroRepository.findByNombreContaining("pollo").isEmpty());
        assertTrue(pasajeroRepository.findByNombreContaining("pollo").size() == 2);
    }

    @Test
    void whenPasajeroWithNidExists_thenShouldBeFound(){
        pasajeroRepository.save(Utilidad.crearPasajero("pasajeroTA", "123abc"));
        assertTrue(pasajeroRepository.existsByNid("123abc"));
    }

    @Test
    void givenMultiplePasajerosWithSameName_whenCounting_thenReturnsCorrectCount(){
        pasajeroRepository.save(Utilidad.crearPasajero("pasajeroT", "123abc"));
        pasajeroRepository.save(Utilidad.crearPasajero("pollo", "122abc"));
        pasajeroRepository.save(Utilidad.crearPasajero("pollo", "133abc"));
        assertTrue(pasajeroRepository.countByNombre("pollo") == 2);
    }

    @Test
    void whenFindingPasajeroByPasaporteNumero_thenReturnsCorrectPasajero(){
        Utilidad.crearPasajeroConPasaporte(pasajeroRepository,pasaporteRepository);
        assertEquals("abc123",pasajeroRepository.findByPasaporteNumero("xyz123").get().getNid());
    }

    @Test
    void whenSearchingPasajerosByPartialName_thenItReturnsMatchingPasajeros(){
        pasajeroRepository.save(Utilidad.crearPasajero("pasajeroT", "123abc"));
        pasajeroRepository.save(Utilidad.crearPasajero("polloTA", "122abc"));
        pasajeroRepository.save(Utilidad.crearPasajero("polloTB", "133abc"));

        assertTrue(!pasajeroRepository.searchByPartialName("pollo").isEmpty());
        assertTrue(pasajeroRepository.searchByPartialName("pollo").size() == 2);
    }

    @Test
    void whenAtLeastOnePasajeroWithPasaporteExists_thenShouldBeTrue(){
        Utilidad.crearPasajeroConPasaporte(pasajeroRepository,pasaporteRepository);
        assertTrue(pasajeroRepository.existsWithPasaporte());
    }

    @Test
    void givenPasajero_whenFindingByNidAndNombre_thenReturnsCorrectPasajero(){
        Pasajero pasajero = pasajeroRepository.save(Utilidad.crearPasajero("pasajeroT", "123abc"));
        assertTrue(pasajeroRepository.findById(pasajero.getId()).isPresent());
        assertTrue(pasajeroRepository.findByNidAndNombre("123abc", "pasajeroT").isPresent());
    }

    @Test
    void givenPasajero_whenDeletingByNId_thenShouldBeDeleted(){
        Pasajero pasajero = pasajeroRepository.save(Utilidad.crearPasajero("pasajeroT", "123abc"));
        assertTrue(pasajeroRepository.findById(pasajero.getId()).isPresent());
        pasajeroRepository.deleteByNId("123abc");
        pasajeroRepository.flush();
        assertTrue(pasajeroRepository.findByNid("123abc").isEmpty());
        //assertFalse(pasajeroRepository.findById(pasajero.getId()).isPresent());
    }




}