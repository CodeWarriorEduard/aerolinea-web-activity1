package com.rafael.actividad1.service.impl;

import com.rafael.actividad1.repository.PasajeroRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class PasajeroServiceImplTest {

    @Mock
    PasajeroRepository pasajeroRepository;

    @InjectMocks
    PasajeroServiceImpl pasajeroServiceImpl;

    @Test
    void findByNombre() {
    }

    @Test
    void findByNid() {
    }

    @Test
    void findByNombreContaining() {
    }

    @Test
    void existsByNid() {
    }

    @Test
    void countByNombre() {
    }

    @Test
    void findByPasaporteNumero() {
    }

    @Test
    void searchByPartialName() {
    }

    @Test
    void existsWithPasaporte() {
    }

    @Test
    void findByNidAndNombre() {
    }

    @Test
    void deleteByNId() {
    }
}