package com.rafael.actividad1.repository;

import com.rafael.actividad1.config.TestContainerConfig;
import com.rafael.actividad1.entity.Pasajero;
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
class PasajeroRepositoryTest {

//    @Container
//    public static PostgreSQLContainer<?> postgres = new PostgreSQLContainer<>("postgres:latest")
//            .withDatabaseName("testdb")
//            .withUsername("testuser")
//            .withPassword("testpass");

    @Autowired
    PasajeroRepository pasajeroRepository;

//        @Test
//        void testDockerContainer() {
//            try (PostgreSQLContainer<?> postgres = new PostgreSQLContainer<>("postgres:15")) {
//                postgres.start();
//                System.out.println("Docker funciona correctamente con Testcontainers");
//            }
//        }

    @Test
    void givenAPasajeroThenSaveItAssertIsTheSame(){
        Pasajero pasajero = Pasajero.builder()
                .nombre("aerolineaT")
                .nid("123abc")
                .build();

        Pasajero savedPasajero = pasajeroRepository.save(pasajero);
        assertEquals(pasajero.getNid(), savedPasajero.getNid());
    }

}