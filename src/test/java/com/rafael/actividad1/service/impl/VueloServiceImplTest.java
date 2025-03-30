package com.rafael.actividad1.service.impl;

import com.rafael.actividad1.dto.request.VueloRequestDTO;
import com.rafael.actividad1.dto.response.VueloResponseDTO;
import com.rafael.actividad1.entity.Aerolinea;
import com.rafael.actividad1.entity.Vuelo;
import com.rafael.actividad1.mapper.VueloMapper;
import com.rafael.actividad1.repository.VueloRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class VueloServiceImplTest {

    @Mock
    VueloRepository vueloRepository;

    @Mock
    VueloMapper vueloMapper;

    @InjectMocks
    VueloServiceImpl vueloService;

    @Test
    void findById() {

        Long id = 1L;
        UUID uuid = UUID.randomUUID();

        Vuelo vuelo = Vuelo.builder()
                .id(id)
                .numeroVuelo(uuid)
                .origen("origen1")
                .destino("destino1")
                .build();

        VueloResponseDTO vueloResponseDTO = new VueloResponseDTO(uuid,"origen1", "destino1");
        when(vueloRepository.findById(id)).thenReturn(Optional.of(vuelo));
        when(vueloMapper.vueloToVueloResponseDTO(vuelo)).thenReturn(vueloResponseDTO);
        VueloResponseDTO findedVueloDTO = vueloService.findById(id);
        assertEquals(findedVueloDTO.numeroVuelo(), uuid, "Vuelo uuid mismatch");
        verify(vueloRepository,times(1)).findById(id);

    }

    @Test
    void findVuelosByDestino() {

        UUID uuid = UUID.randomUUID();
        UUID uuid2 = UUID.randomUUID();

        Vuelo vuelo = Vuelo.builder()
                .id(1L)
                .numeroVuelo(uuid)
                .origen("origen1")
                .destino("destino1")
                .build();

        Vuelo vuelo2 = Vuelo.builder()
                .id(2L)
                .numeroVuelo(uuid2)
                .origen("origen2")
                .destino("destino1")
                .build();

        VueloResponseDTO vueloResponseDTO = new VueloResponseDTO(uuid,"origen1", "destino1");
        VueloResponseDTO vueloResponseDTO2 = new VueloResponseDTO(uuid2,"origen2", "destino2");

        when(vueloRepository.findVuelosByDestino("destino1")).thenReturn(List.of(vuelo, vuelo2));
        when(vueloMapper.vueloToVueloResponseDTO(vuelo)).thenReturn(vueloResponseDTO);
        when(vueloMapper.vueloToVueloResponseDTO(vuelo2)).thenReturn(vueloResponseDTO2);
        List<VueloResponseDTO> findedVuelos = vueloService.findVuelosByDestino("destino1");
        assertEquals(findedVuelos.size(), 2);
        verify(vueloRepository,times(1)).findVuelosByDestino("destino1");
    }

    @Test
    void save() {
        UUID uuid = UUID.randomUUID();

        Vuelo vuelo = Vuelo.builder()
                .id(1L)
                .numeroVuelo(uuid)
                .origen("origen1")
                .destino("destino1")
                .build();

        VueloRequestDTO vueloRequestDTO = new VueloRequestDTO(uuid, "origen1", "destino1", null, null);

        VueloResponseDTO vueloResponseDTO = new VueloResponseDTO(uuid, "origen1", "destino1");

        when(vueloMapper.vueloRequestDTOToVuelo(vueloRequestDTO)).thenReturn(vuelo);
        when(vueloMapper.vueloToVueloResponseDTO(vuelo)).thenReturn(vueloResponseDTO);
        when(vueloRepository.save(vuelo)).thenReturn(vuelo);

        VueloResponseDTO savedVueloResponseDTO = vueloService.save(vueloRequestDTO);
        assertEquals(savedVueloResponseDTO.numeroVuelo(), uuid, "Vuelo uuid mismatch");
        verify(vueloRepository,times(1)).save(vuelo);
    }

    @Test
    void findAll() {

        UUID uuid = UUID.randomUUID();
        UUID uuid2 = UUID.randomUUID();

        Vuelo vuelo = Vuelo.builder()
                .id(1L)
                .numeroVuelo(uuid)
                .origen("origen1")
                .destino("destino1")
                .build();

        Vuelo vuelo2 = Vuelo.builder()
                .id(2L)
                .numeroVuelo(uuid2)
                .origen("origen2")
                .destino("destino2")
                .build();

        VueloResponseDTO vueloResponseDTO = new VueloResponseDTO(uuid,"origen1", "destino1");
        VueloResponseDTO vueloResponseDTO2 = new VueloResponseDTO(uuid2,"origen2", "destino2");

        when(vueloRepository.findAll()).thenReturn(List.of(vuelo, vuelo2));
        when(vueloMapper.vueloToVueloResponseDTO(vuelo)).thenReturn(vueloResponseDTO);
        when(vueloMapper.vueloToVueloResponseDTO(vuelo2)).thenReturn(vueloResponseDTO2);
        List<VueloResponseDTO> findedVuelos = vueloService.findAll();
        assertEquals(findedVuelos.size(), 2);
        verify(vueloRepository,times(1)).findAll();
    }

    @Test
    void countVuelosByDestino() {

        when(vueloRepository.countVuelosByDestino("destino1")).thenReturn(2L);
        assertEquals(vueloRepository.countVuelosByDestino("destino1"), 2L);
        verify(vueloRepository,times(1)).countVuelosByDestino("destino1");
    }

    @Test
    void findVuelosByOrigen() {

        UUID uuid = UUID.randomUUID();
        UUID uuid2 = UUID.randomUUID();

        Vuelo vuelo = Vuelo.builder()
                .id(1L)
                .numeroVuelo(uuid)
                .origen("origen1")
                .destino("destino1")
                .build();

        Vuelo vuelo2 = Vuelo.builder()
                .id(2L)
                .numeroVuelo(uuid2)
                .origen("origen1")
                .destino("destino2")
                .build();

        VueloResponseDTO vueloResponseDTO = new VueloResponseDTO(uuid,"origen1", "destino1");
        VueloResponseDTO vueloResponseDTO2 = new VueloResponseDTO(uuid2,"origen1", "destino2");

        when(vueloRepository.findVuelosByOrigen("origen1")).thenReturn(List.of(vuelo, vuelo2));
        when(vueloMapper.vueloToVueloResponseDTO(vuelo)).thenReturn(vueloResponseDTO);
        when(vueloMapper.vueloToVueloResponseDTO(vuelo2)).thenReturn(vueloResponseDTO2);
        List<VueloResponseDTO> findedVuelos = vueloService.findVuelosByOrigen("origen1");
        assertEquals(findedVuelos.size(), 2);
        verify(vueloRepository,times(1)).findVuelosByOrigen("origen1");

    }

    @Test
    void vuelosOrderedByDestino() {
        UUID uuid = UUID.randomUUID();
        UUID uuid2 = UUID.randomUUID();

        Vuelo vuelo = Vuelo.builder()
                .id(1L)
                .numeroVuelo(uuid)
                .origen("origen1")
                .destino("destino1")
                .build();

        Vuelo vuelo2 = Vuelo.builder()
                .id(2L)
                .numeroVuelo(uuid2)
                .origen("origen1")
                .destino("destino2")
                .build();

        VueloResponseDTO vueloResponseDTO = new VueloResponseDTO(uuid,"origen1", "destino1");
        VueloResponseDTO vueloResponseDTO2 = new VueloResponseDTO(uuid2,"origen1", "destino2");

        when(vueloRepository.vuelosOrderedByDestino()).thenReturn(List.of(vuelo, vuelo2));
        when(vueloMapper.vueloToVueloResponseDTO(vuelo)).thenReturn(vueloResponseDTO);
        when(vueloMapper.vueloToVueloResponseDTO(vuelo2)).thenReturn(vueloResponseDTO2);
        List<VueloResponseDTO> findedVuelos = vueloService.vuelosOrderedByDestino();
        assertEquals(findedVuelos.size(), 2);
        verify(vueloRepository,times(1)).vuelosOrderedByDestino();

    }

    @Test
    void findVueloByNumeroVuelo() {
        UUID uuid = UUID.randomUUID();
        Vuelo vuelo = Vuelo.builder()
                .id(1L)
                .numeroVuelo(uuid)
                .origen("origen1")
                .destino("destino1")
                .build();

        VueloResponseDTO vueloResponseDTO = new VueloResponseDTO(uuid,"origen1", "destino1");
        when(vueloMapper.vueloToVueloResponseDTO(vuelo)).thenReturn(vueloResponseDTO);
        when(vueloRepository.findVueloByNumeroVuelo(uuid)).thenReturn(Optional.of(vuelo));
        VueloResponseDTO findedVueloResponseDTO = vueloService.findVueloByNumeroVuelo(uuid);
        assertEquals(findedVueloResponseDTO.numeroVuelo(), vueloResponseDTO.numeroVuelo());
        verify(vueloRepository,times(1)).findVueloByNumeroVuelo(uuid);
    }

    @Test
    void findVuelosByDestinoStartsWith() {

        UUID uuid = UUID.randomUUID();
        UUID uuid2 = UUID.randomUUID();

        Vuelo vuelo = Vuelo.builder()
                .id(1L)
                .numeroVuelo(uuid)
                .origen("origen1")
                .destino("destino1")
                .build();

        Vuelo vuelo2 = Vuelo.builder()
                .id(2L)
                .numeroVuelo(uuid2)
                .origen("origen1")
                .destino("destino2")
                .build();

        VueloResponseDTO vueloResponseDTO = new VueloResponseDTO(uuid,"origen1", "destino1");
        VueloResponseDTO vueloResponseDTO2 = new VueloResponseDTO(uuid2,"origen1", "destino2");

        when(vueloRepository.findVuelosByDestinoStartsWith("d")).thenReturn(List.of(vuelo, vuelo2));
        when(vueloMapper.vueloToVueloResponseDTO(vuelo)).thenReturn(vueloResponseDTO);
        when(vueloMapper.vueloToVueloResponseDTO(vuelo2)).thenReturn(vueloResponseDTO2);
        List<VueloResponseDTO> findedVuelos = vueloService.findVuelosByDestinoStartsWith("d");
        assertEquals(findedVuelos.size(), 2);
        verify(vueloRepository,times(1)).findVuelosByDestinoStartsWith("d");
    }

    @Test
    void numberOfVueloByNameOfAerolinea() {
        UUID uuid = UUID.randomUUID();

        Aerolinea aerolinea = Aerolinea.builder()
                .nombre("aerolina1")
                .build();

        Vuelo vuelo = Vuelo.builder()
                .id(1L)
                .numeroVuelo(uuid)
                .origen("origen1")
                .destino("destino1")
                .aerolineas(List.of(aerolinea))
                .build();

        when(vueloRepository.numberOfVueloByNameOfAerolinea("aerolinea1")).thenReturn(1L);
        Long result = vueloService.numberOfVueloByNameOfAerolinea("aerolinea1");
        assertEquals(result, 1L);
        verify(vueloRepository,times(1)).numberOfVueloByNameOfAerolinea("aerolinea1");

    }
}