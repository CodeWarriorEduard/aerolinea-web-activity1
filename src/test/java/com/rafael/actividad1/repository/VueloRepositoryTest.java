package com.rafael.actividad1.repository;

import com.rafael.actividad1.config.TestContainerConfig;
import com.rafael.actividad1.entity.Aerolinea;
import com.rafael.actividad1.entity.Vuelo;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.jdbc.Sql;
import org.testcontainers.junit.jupiter.Testcontainers;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

@Import(TestContainerConfig.class)
@Testcontainers
@AutoConfigureTestDatabase(replace =  AutoConfigureTestDatabase.Replace.NONE)
@DataJpaTest
@Sql(scripts = "classpath:schema.sql")
class VueloRepositoryTest {

    @Autowired
    private VueloRepository vueloRepository;

    @Test
    void findByIdTest() {
        Optional<Vuelo> vuelo = vueloRepository.findById(1L);
        assertTrue(vuelo.isPresent());
        assertEquals("Nueva York", vuelo.get().getDestino());
    }

    @Test
    void findVueloByAerolineasTest() {
        List<Vuelo> vuelos = vueloRepository.findVuelosByDestino("Nueva York");
        assertFalse(vuelos.isEmpty(), "Ningun vuelo con destino el destino dado");
        assertEquals(1, vuelos.size());
    }

    @Test
    void saveTest() {

        Vuelo newVuelo = Vuelo.builder()
                .destino("Colombia")
                .origen("Estados Unidos")
                .numeroVuelo(UUID.randomUUID())
                .build();

        Vuelo savingVuelo = vueloRepository.save(newVuelo);

        // Verificamos que exista

        Optional<Vuelo> result = vueloRepository.findById(savingVuelo.getId());
        assertTrue(result.isPresent());
    }

    @Test
    void findAllTest() {
        List<Vuelo> vuelos = vueloRepository.findAll();
        assertEquals(11, vuelos.size());
    }

    @Test
    void countVuelosByDestinoTest() {
        String destino = "Miami";
        Long vuelosByDestino = vueloRepository.countVuelosByDestino(destino);
        assertEquals(1, vuelosByDestino);
    }

    @Test
    void findVueloByOrigenTest() {
        String origen = "Madrid";
        List<Vuelo> vuelosByOrigen = vueloRepository.findVueloByOrigen(origen);

        // Existe un vuelo desde madrid entonces pasará el test
        assertFalse(vuelosByOrigen.isEmpty());

        for ( Vuelo vuelo: vuelosByOrigen){
            assertEquals(origen, vuelo.getOrigen());
        }

    }

    @Test
    void vuelosOrderedByDestinoTest() {

        List<Vuelo> vuelosOrderedByDestino = vueloRepository.vuelosOrderedByDestino();

        List<String> destinos = List.of(
                "Berlin",
                "Buenos Aires",
                "Dubai",
                "Londres",
                "Miami",
                "Moscú",
                "Nueva York",
                "Seúl",
                "Sidney",
                "Tokio",
                "Toronto"
        );


        for (int i = 0; i < vuelosOrderedByDestino.size(); i++) {
            assertEquals(destinos.get(i), vuelosOrderedByDestino.get(i).getDestino());
        }

    }

    @Test
    void findVueloByNumeroVueloTest() {

        UUID numVuelo = UUID.fromString("c8b6762f-3082-43af-9e2a-75b3d21d0d8c");
        Optional<Vuelo> vuelo = vueloRepository.findVueloByNumeroVuelo(numVuelo);
        assertTrue(vuelo.isPresent());
        assertEquals("Paris", vuelo.get().getOrigen());
        assertEquals("Londres", vuelo.get().getDestino());
    }

    @Test
    void findVueloByOrigenStartsWithTest() {
        String letter = "N";
        List<Vuelo> vuelos = vueloRepository.findVueloByDestinoStartsWith(letter);

        assertEquals(1, vuelos.size());

        for (Vuelo vuelo: vuelos){
            assertTrue(vuelo.getDestino().startsWith(letter));
        }
    }

    @Test
    void numberOfVueloByNameOfAerolineaTest() {
      Long numVuelo = vueloRepository.numberOfVueloByNameOfAerolinea("AeroJet");
      assertEquals(2, numVuelo);
    }
}