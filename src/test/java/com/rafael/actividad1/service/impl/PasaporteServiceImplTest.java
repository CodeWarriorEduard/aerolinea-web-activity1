package com.rafael.actividad1.service.impl;

import com.rafael.actividad1.dto.response.PasajeroResponseDTO;
import com.rafael.actividad1.dto.response.PasaporteResponseDTO;
import com.rafael.actividad1.entity.Pasajero;
import com.rafael.actividad1.entity.Pasaporte;
import com.rafael.actividad1.mapper.PasaporteMapper;
import com.rafael.actividad1.repository.PasaporteRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class PasaporteServiceImplTest {

    @Mock
    PasaporteRepository pasaporteRepository;

    @Mock
    PasaporteMapper pasaporteMapper;

    @InjectMocks
    PasaporteServiceImpl pasaporteServiceImpl;

    @Test
    void findByNumero() {

        Pasaporte pasaporte = Pasaporte.builder()
                .numero("1a")
                .build();

        PasaporteResponseDTO pasaporteResponseDTO = new PasaporteResponseDTO("1a",null);
        when(pasaporteRepository.findByNumero("1a")).thenReturn(Optional.of(pasaporte));
        when(pasaporteMapper.pasaporteToPasaporteResponseDTO(pasaporte)).thenReturn(pasaporteResponseDTO);
        PasaporteResponseDTO responseDTO = pasaporteServiceImpl.findByNumero("1a");
        assertEquals(responseDTO.numero(), "1a");
        verify(pasaporteRepository,times(1)).findByNumero("1a");
    }

    @Test
    void findByPasajeroId() {

        Pasajero pasajero = Pasajero.builder()
                .id(1L)
                .nombre("pollo1")
                .build();

        Pasaporte pasaporte = Pasaporte.builder()
                .numero("1a")
                .pasajero(pasajero)
                .build();

        PasajeroResponseDTO pasajeroResponseDTO = new PasajeroResponseDTO("1a",null);
        PasaporteResponseDTO pasaporteResponseDTO = new PasaporteResponseDTO("1a",pasajeroResponseDTO);
        when(pasaporteMapper.pasaporteToPasaporteResponseDTO(pasaporte)).thenReturn(pasaporteResponseDTO);
        when(pasaporteRepository.findByPasajeroId(1L)).thenReturn(Optional.of(pasaporte));
        PasaporteResponseDTO responseDTO = pasaporteServiceImpl.findByPasajeroId(1L);
        assertEquals(responseDTO.numero(), "1a");
        verify(pasaporteRepository,times(1)).findByPasajeroId(1L);
    }

    @Test
    void existsByNumero() {

        when(pasaporteRepository.existsByNumero("1a")).thenReturn(true);
        boolean exists = pasaporteServiceImpl.existsByNumero("1a");
        assertTrue(exists);
        verify(pasaporteRepository,times(1)).existsByNumero("1a");
    }

    @Test
    void findByPasajeroNombre() {

        Pasajero pasajero = Pasajero.builder()
                .id(1L)
                .nombre("pollo1")
                .build();

        Pasaporte pasaporte = Pasaporte.builder()
                .numero("1a")
                .pasajero(pasajero)
                .build();

        PasajeroResponseDTO pasajeroResponseDTO = new PasajeroResponseDTO("1a",null);
        PasaporteResponseDTO pasaporteResponseDTO = new PasaporteResponseDTO("1a",pasajeroResponseDTO);
        when(pasaporteMapper.pasaporteToPasaporteResponseDTO(pasaporte)).thenReturn(pasaporteResponseDTO);
        when(pasaporteRepository.findByPasajeroNombre("pollo1")).thenReturn(List.of(pasaporte));
        List<PasaporteResponseDTO> responseDTO = pasaporteServiceImpl.findByPasajeroNombre("pollo1");
        assertEquals(responseDTO.size(), 1);
        verify(pasaporteRepository,times(1)).findByPasajeroNombre("pollo1");
    }

    @Test
    void deleteByNumero() {

        when(pasaporteRepository.deleteByNumero("1a")).thenReturn(1);
        when(pasaporteRepository.existsByNumero("1a")).thenReturn(true);
        int result = pasaporteServiceImpl.deleteByNumero("1a");
        assertEquals(result, 1);
        verify(pasaporteRepository,times(1)).deleteByNumero("1a");
    }

    @Test
    void findByPasajeroNId() {

        Pasajero pasajero = Pasajero.builder()
                .id(1L)
                .nid("a1")
                .nombre("pollo1")
                .build();

        Pasaporte pasaporte = Pasaporte.builder()
                .numero("1a")
                .pasajero(pasajero)
                .build();

        PasajeroResponseDTO pasajeroResponseDTO = new PasajeroResponseDTO("1a","a1");
        PasaporteResponseDTO pasaporteResponseDTO = new PasaporteResponseDTO("1a",pasajeroResponseDTO);
        when(pasaporteMapper.pasaporteToPasaporteResponseDTO(pasaporte)).thenReturn(pasaporteResponseDTO);
        when(pasaporteRepository.findByPasajeroNId("a1")).thenReturn(Optional.of(pasaporte));
        PasaporteResponseDTO responseDTO = pasaporteServiceImpl.findByPasajeroNId("a1");
        assertEquals(responseDTO.numero(), "1a");
        verify(pasaporteRepository,times(1)).findByPasajeroNId("a1");
    }

    @Test
    void searchByPartialNumber() {

        Pasaporte pasaporte = Pasaporte.builder()
                .numero("1a")
                .build();
        Pasaporte pasaporte2 = Pasaporte.builder()
                .numero("1b")
                .build();

        PasaporteResponseDTO pasaporteResponseDTO = new PasaporteResponseDTO("1a",null);
        PasaporteResponseDTO pasaporteResponseDTO2 = new PasaporteResponseDTO("1b",null);

        when(pasaporteRepository.searchByPartialNumber("1")).thenReturn(List.of(pasaporte, pasaporte2));
        when(pasaporteMapper.pasaporteToPasaporteResponseDTO(pasaporte)).thenReturn(pasaporteResponseDTO);
        when(pasaporteMapper.pasaporteToPasaporteResponseDTO(pasaporte2)).thenReturn(pasaporteResponseDTO2);

        List<PasaporteResponseDTO> pasaportesResponseDTO = pasaporteServiceImpl.searchByPartialNumber("1");
        assertEquals(pasaportesResponseDTO.size(), 2);
        verify(pasaporteRepository,times(1)).searchByPartialNumber("1");

    }

    @Test
    void findByPasajeroNombreAndNid() {

        Pasajero pasajero = Pasajero.builder()
                .id(1L)
                .nid("a1")
                .nombre("pollo1")
                .build();

        Pasaporte pasaporte = Pasaporte.builder()
                .numero("1a")
                .pasajero(pasajero)
                .build();

        PasajeroResponseDTO pasajeroResponseDTO = new PasajeroResponseDTO("1a","a1");
        PasaporteResponseDTO pasaporteResponseDTO = new PasaporteResponseDTO("1a",pasajeroResponseDTO);
        when(pasaporteMapper.pasaporteToPasaporteResponseDTO(pasaporte)).thenReturn(pasaporteResponseDTO);
        when(pasaporteRepository.findByPasajeroNombreAndNid("pollo1", "a1")).thenReturn(Optional.of(pasaporte));
        PasaporteResponseDTO responseDTO = pasaporteServiceImpl.findByPasajeroNombreAndNid("pollo1", "a1");
        assertEquals(responseDTO.numero(), "1a");
        verify(pasaporteRepository,times(1)).findByPasajeroNombreAndNid("pollo1", "a1");
    }

    @Test
    void existsByPasajeroNid() {

        when(pasaporteRepository.existsByPasajeroNid("a1")).thenReturn(true);
        boolean result = pasaporteServiceImpl.existsByPasajeroNid("a1");
        assertTrue(result);
        verify(pasaporteRepository,times(1)).existsByPasajeroNid("a1");

    }

    @Test
    void deleteByPasajeroNid() {
        when(pasaporteRepository.deleteByPasajeroNid("a1")).thenReturn(1);
        when(pasaporteRepository.existsByPasajeroNid("a1")).thenReturn(true);
        int result = pasaporteServiceImpl.deleteByPasajeroNid("a1");
        assertEquals(result, 1);
        verify(pasaporteRepository,times(1)).deleteByPasajeroNid("a1");
    }
}