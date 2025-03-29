package com.rafael.actividad1.service.impl;

import com.rafael.actividad1.dto.request.PasajeroRequestDTO;
import com.rafael.actividad1.dto.request.PasaporteRequestDTO;
import com.rafael.actividad1.entity.Pasajero;
import com.rafael.actividad1.entity.Pasaporte;
import com.rafael.actividad1.mapper.PasajeroMapper;
import com.rafael.actividad1.repository.PasajeroRepository;
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
class PasajeroServiceImplTest {

    @Mock
    PasajeroRepository pasajeroRepository;

    @Mock
    PasajeroMapper pasajeroMapper;

    @InjectMocks
    PasajeroServiceImpl pasajeroServiceImpl;

    @Test
    void findByNombre_Test() {
        String nombre = "pollo test";

        Pasajero pasajero = Pasajero.builder()
                .nid("1a")
                .nombre(nombre)
                .build();
        PasajeroRequestDTO pasajeroDTO = new PasajeroRequestDTO(nombre,"1a",null);

        when(pasajeroRepository.findByNombre(nombre)).thenReturn(Optional.of(pasajero));
        when(pasajeroMapper.pasajeroToPasajeroDTO(pasajero)).thenReturn(pasajeroDTO);
        PasajeroRequestDTO findedPasajeroDTO = pasajeroServiceImpl.findByNombre(nombre);
        assertTrue(findedPasajeroDTO.nid().equals("1a"));
        verify(pasajeroRepository, times(1)).findByNombre(nombre);
    }

    @Test
    void findByNid() {
        String nid = "1a";

        Pasajero pasajero = Pasajero.builder()
                .nid(nid)
                .nombre("pollo test")
                .build();
        PasajeroRequestDTO pasajeroDTO = new PasajeroRequestDTO("pollo test",nid,null);

        when(pasajeroRepository.findByNid(nid)).thenReturn(Optional.of(pasajero));
        when(pasajeroMapper.pasajeroToPasajeroDTO(pasajero)).thenReturn(pasajeroDTO);
        PasajeroRequestDTO findedPasajeroDTO = pasajeroServiceImpl.findByNid(nid);
        assertTrue(findedPasajeroDTO.nid().equals(nid));
        verify(pasajeroRepository, times(1)).findByNid(nid);
    }

    @Test
    void findByNombreContaining() {

        Pasajero pasajero = Pasajero.builder()
                .nombre("pollo test")
                .build();
        Pasajero pasajero2 = Pasajero.builder()
                .nombre("pollo test2")
                .build();
        PasajeroRequestDTO pasajeroDTO = new PasajeroRequestDTO("pollo test",null,null);
        PasajeroRequestDTO pasajeroDTO2 = new PasajeroRequestDTO("pollo test2",null,null);

        when(pasajeroRepository.findByNombreContaining("pollo")).thenReturn(List.of(pasajero, pasajero2));
        when(pasajeroMapper.pasajeroToPasajeroDTO(pasajero)).thenReturn(pasajeroDTO);
        when(pasajeroMapper.pasajeroToPasajeroDTO(pasajero2)).thenReturn(pasajeroDTO2);
        List<PasajeroRequestDTO> findedPasajerosDTOs = pasajeroServiceImpl.findByNombreContaining("pollo");
        assertTrue(findedPasajerosDTOs.size() == 2);
        verify(pasajeroRepository, times(1)).findByNombreContaining("pollo");
    }

    @Test
    void existsByNid() {
        when(pasajeroRepository.existsByNid("1a")).thenReturn(true);
        boolean response = pasajeroServiceImpl.existsByNid("1a");
        assertTrue(response);
        verify(pasajeroRepository, times(1)).existsByNid("1a");
    }

    @Test
    void countByNombre() {
        when(pasajeroRepository.countByNombre("pollo")).thenReturn(2);
        int response = pasajeroServiceImpl.countByNombre("pollo");
        assertTrue(response == 2);
        verify(pasajeroRepository, times(1)).countByNombre("pollo");
    }

    @Test
    void findByPasaporteNumero() {

        String nombre = "pollo test";

        Pasajero pasajero = Pasajero.builder()
                .nid("1a")
                .nombre(nombre)
                .build();
        Pasaporte pasaporte = Pasaporte.builder()
                .numero("1ab")
                .pasajero(pasajero)
                .build();
        pasajero.setPasaporte(pasaporte);
        PasaporteRequestDTO pasaporteRequestDTO = new PasaporteRequestDTO("1ab",null);
        PasajeroRequestDTO pasajeroDTO = new PasajeroRequestDTO(nombre,"1a", pasaporteRequestDTO);
        when(pasajeroMapper.pasajeroToPasajeroDTO(pasajero)).thenReturn(pasajeroDTO);
        when(pasajeroRepository.findByPasaporteNumero("1ab")).thenReturn(Optional.of(pasajero));

        PasajeroRequestDTO findedPasajeroDTO = pasajeroServiceImpl.findByPasaporteNumero("1ab");
        assertTrue(findedPasajeroDTO.nid().equals(pasajero.getNid()));
        verify(pasajeroRepository, times(1)).findByPasaporteNumero("1ab");

    }

    @Test
    void searchByPartialName() {
        Pasajero pasajero = Pasajero.builder()
                .nombre("pollo test")
                .build();
        Pasajero pasajero2 = Pasajero.builder()
                .nombre("pollo test2")
                .build();
        PasajeroRequestDTO pasajeroDTO = new PasajeroRequestDTO("pollo test",null,null);
        PasajeroRequestDTO pasajeroDTO2 = new PasajeroRequestDTO("pollo test2",null,null);

        when(pasajeroRepository.searchByPartialName("pollo")).thenReturn(List.of(pasajero, pasajero2));
        when(pasajeroMapper.pasajeroToPasajeroDTO(pasajero)).thenReturn(pasajeroDTO);
        when(pasajeroMapper.pasajeroToPasajeroDTO(pasajero2)).thenReturn(pasajeroDTO2);
        List<PasajeroRequestDTO> findedPasajerosDTOs = pasajeroServiceImpl.searchByPartialName("pollo");
        assertTrue(findedPasajerosDTOs.size() == 2);
        verify(pasajeroRepository, times(1)).searchByPartialName("pollo");
    }

    @Test
    void existsWithPasaporte() {
        when(pasajeroRepository.existsWithPasaporte()).thenReturn(true);
        boolean exists = pasajeroServiceImpl.existsWithPasaporte();
        assertTrue(exists);
        verify(pasajeroRepository, times(1)).existsWithPasaporte();

    }

    @Test
    void findByNidAndNombre() {
        String nombre = "pollo test";

        Pasajero pasajero = Pasajero.builder()
                .nid("1a")
                .nombre(nombre)
                .build();
        PasajeroRequestDTO pasajeroDTO = new PasajeroRequestDTO(nombre,"1a",null);

        when(pasajeroRepository.findByNidAndNombre("1a",nombre)).thenReturn(Optional.of(pasajero));
        when(pasajeroMapper.pasajeroToPasajeroDTO(pasajero)).thenReturn(pasajeroDTO);
        PasajeroRequestDTO findedPasajeroDTO = pasajeroServiceImpl.findByNidAndNombre("1a",nombre);
        assertTrue(findedPasajeroDTO.nid().equals("1a"));
        verify(pasajeroRepository, times(1)).findByNidAndNombre("1a",nombre);
    }

    @Test
    void deleteByNId() {

        String nid = "1a";

        Pasajero pasajero = Pasajero.builder()
                .nombre("pollo test")
                .nid(nid)
                .build();

        when(pasajeroRepository.findByNid(nid)).thenReturn(Optional.of(pasajero));
        doNothing().when(pasajeroRepository).deleteByNId(nid);
        boolean success = pasajeroServiceImpl.deleteByNId("1a");

        assertTrue(success);
        verify(pasajeroRepository, times(1)).findByNid(nid);
        verify(pasajeroRepository, times(1)).deleteByNId(nid);
    }
}