package com.rafael.actividad1.repository;

import com.rafael.actividad1.config.TestContainerConfig;
import org.junit.jupiter.api.Assertions;
import static  org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.jdbc.Sql;
import org.testcontainers.junit.jupiter.Testcontainers;



// Ver que no es necesario.
@Import(TestContainerConfig.class)
@Testcontainers
@AutoConfigureTestDatabase(replace =  AutoConfigureTestDatabase.Replace.NONE)
@DataJpaTest
@Sql("../../../../../resources/schema.sql")
class AerolineaRepositoryTest {

    @Autowired
    private AerolineaRepository aerolineaRepository;

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Test
    void whenCountingAllAerolineas_thenReturnCorrectCount(){
        Long count = aerolineaRepository.countAllAerolineas();
        assertEquals(10, count);

    }
}