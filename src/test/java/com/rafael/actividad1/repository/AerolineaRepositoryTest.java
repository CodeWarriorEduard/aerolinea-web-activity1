package com.rafael.actividad1.repository;

import com.rafael.actividad1.config.TestContainerConfig;
import com.rafael.actividad1.entity.Aerolinea;
import jakarta.transaction.Transactional;
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

import static org.junit.jupiter.api.Assertions.*;



@Import(TestContainerConfig.class)
@Testcontainers
@AutoConfigureTestDatabase(replace =  AutoConfigureTestDatabase.Replace.NONE)
@DataJpaTest
@Sql(scripts = "classpath:schema.sql")
class AerolineaRepositoryTest {

    @Autowired
    private AerolineaRepository aerolineaRepository;

    @Autowired
    private JdbcTemplate jdbcTemplate;


    @Test
    void whenDeletingById_GivenIdThenConfirmItsDeleted(){
        // Borrar aerolinea
        aerolineaRepository.deleteById(1L);

        // Verificar que se halla borrado
        Optional<Aerolinea> aerolinea = aerolineaRepository.findById(1L);

        assertTrue(aerolinea.isEmpty());
    }

    @Test
    void whenFindingById_GivenAnIdThenConfirmItExist(){

        Optional<Aerolinea> aerolinea = aerolineaRepository.findById(2L);

        assertTrue(aerolinea.isPresent());
        assertEquals("SkyHigh Airlines", aerolinea.get().getNombre());

    }

    @Test
    void whenFindingById_GivenAnIdThenConfirmItDoesntExist(){
        Optional<Aerolinea> aerolinea = aerolineaRepository.findById(11L);
        assertTrue(aerolinea.isEmpty());
    }


    @Test
    @Transactional
    void whenSavingAerolinea_ThenConfirmItsSaved(){
        Aerolinea aerolinea = Aerolinea.builder()
                .nombre("Avianca")
                .vuelos(List.of())
                .build();

        Aerolinea result = aerolineaRepository.save(aerolinea);

        // Verificar que se guardó correctamente

        Optional<Aerolinea> aerolinea1 = aerolineaRepository.findById(result.getId());

        assertTrue(aerolinea1.isPresent());
        assertEquals("Avianca", aerolinea1.get().getNombre());

    }

    @Test
    void whenFindingAll_ThenTheListShouldBe10(){
        List<Aerolinea> aerolineas = aerolineaRepository.findAll();
        assertEquals(10, aerolineas.size());
    }

    @Test
    void whenFindingAerolineasStartWithA_ThenConfirmIsTheCorrectList(){
        List<Aerolinea> aerolineas = aerolineaRepository.aerolineaStartsWithA();

        assertEquals(2, aerolineas.size());

        for (Aerolinea aerolinea: aerolineas){
            assertTrue(aerolinea.getNombre().startsWith("A"));
        }

    }

    @Test
    void givenNombre_whenFindingAerolinea_thenShouldReturnMatchingAerolinea(){
        String name = "AeroJet";
        Optional<Aerolinea> aerolinea = aerolineaRepository.findByNombre(name);
        assertTrue(aerolinea.isPresent());
        assertEquals(name, aerolinea.get().getNombre());
    }

    @Test
    void givenPassengerName_whenFindingAerolinea_thenShouldIndicateIfHeHadFlights(){

        String nombre = "Juan Perez";

        List<Aerolinea> aerolineas = aerolineaRepository.findAerolineaByPassengerName(nombre);

        assertFalse(aerolineas.isEmpty(), "No se ha encontrado ninguna aerolinea");

        for (Aerolinea aerolinea: aerolineas){
            assertNotNull(aerolinea.getNombre());
        }

    }

    @Test
    void givenAerolineasWithTwoFlights_whenQuerying_thenShouldReturnCorrectAerolineas(){

        List<Aerolinea> aerolineas = aerolineaRepository.aerolineasWithTwoFlightsx();

        List<String> nombreAerolineas = List.of(
                "SkyHigh Airlines",
                "AeroJet"
        );

        assertFalse(aerolineas.isEmpty(), "Ninguna aerolinea con dos vuelos encontrada");

        for (Aerolinea aerolinea: aerolineas){
            assertTrue(nombreAerolineas.contains(aerolinea.getNombre()));
        }
    }

    @Test
    void givenAerolineas_whenFindAllOrderedByName_thenShouldReturnSortedList(){
        List<Aerolinea> aerolineas = aerolineaRepository.findAllOrderedByName();

        assertFalse(aerolineas.isEmpty(), "La lista de aerolineas no debe estar vacía");

        List<String> expected = List.of(
                "AeroJet",
                "AirNova",
                "BlueWings",
                "CloudAir",
                "EagleFlight",
                "FlyAway",
                "GoldenAir",
                "RapidAir",
                "SkyHigh Airlines",
                "VuelaSeguro"
        );


        for (int i = 0; i < aerolineas.size(); i++) {
            assertEquals(expected.get(i), aerolineas.get(i).getNombre());
        }

    }


    @Test
    void whenCountingAllAerolineas_thenShouldReturnCorrectCount(){
        Long count = aerolineaRepository.countAllAerolineas();
        assertEquals(10, count);
    }
}