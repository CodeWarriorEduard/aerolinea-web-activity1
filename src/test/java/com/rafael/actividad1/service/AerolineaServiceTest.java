package com.rafael.actividad1.service;

import com.rafael.actividad1.entity.Aerolinea;
import com.rafael.actividad1.repository.AerolineaRepository;
import com.rafael.actividad1.service.impl.AerolineaServiceImpl;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;


class AerolineaServiceTest {

    @Mock
    private AerolineaRepository aerolineaRepository;

    @InjectMocks
    private AerolineaServiceImpl aerolineaService;


    public AerolineaServiceTest() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void deleteAerolineaByIdTest(){
        Long id = 114L;

        Aerolinea aerolinea = Aerolinea.builder()
                .id(114L)
                .nombre("Avianca")
                .vuelos(List.of())
                .build();

        // Mocking flujo de la implementación.

        when(aerolineaRepository.findById(id)).thenReturn(Optional.of(aerolinea));

        doNothing().when(aerolineaRepository).deleteById(id);

        boolean response = aerolineaService.deleteAerolineaById(id);
        assertTrue(response);
        verify(aerolineaRepository, times(1)).deleteById(id);
    }

    @Test
    void deleteAerolineaById_NoExists_Test(){
        Long id = 115L;

        // Mocking flujo de la implementación.

        when(aerolineaRepository.findById(id)).thenReturn(Optional.empty());

        doNothing().when(aerolineaRepository).deleteById(id);

        boolean response = aerolineaService.deleteAerolineaById(id);

        assertFalse(response);

        verify(aerolineaRepository, never()).deleteById(anyLong());
    }

    @Test
    void findAerolineaById_Test(){
        Long id = 1L;
        Aerolinea aerolinea = Aerolinea.builder()
                .id(114L)
                .nombre("Avianca")
                .vuelos(List.of())
                .build();

        when(aerolineaRepository.findById(id)).thenReturn(Optional.of(aerolinea));

        Optional<Aerolinea> response = aerolineaService.findAerolineaById(id);

        assertTrue(response.isPresent());

        verify(aerolineaRepository, times(1)).findById(id);

    }

    @Test
    void saveAerolinea_Test(){
        Aerolinea aerolinea = Aerolinea.builder()
                .id(114L)
                .nombre("Avianca")
                .vuelos(List.of())
                .build();

        when(aerolineaRepository.save(aerolinea)).thenReturn(aerolinea);

        Aerolinea response = aerolineaService.saveAerolinea(aerolinea);

        assertEquals(aerolinea, response);
        verify(aerolineaRepository).save(aerolinea);

    }


    @Test
    void findAllAerolineasInDb(){

        Aerolinea aerolinea1 = Aerolinea.builder()
                .id(114L)
                .nombre("Avianca")
                .vuelos(List.of())
                .build();

        Aerolinea aerolinea2 = Aerolinea.builder()
                .id(115L)
                .nombre("Emirates")
                .vuelos(List.of())
                .build();

        List<Aerolinea> aerolineas = List.of(
                aerolinea1,
                aerolinea2
        );

        when(aerolineaRepository.findAll()).thenReturn(aerolineas);

        List<Aerolinea> response = aerolineaService.findAllAerolineasInDb();

        assertEquals(aerolineas, response);

        verify(aerolineaRepository).findAll();

    }


    @Test
    void findAerolineaByNombre_Test(){

        Aerolinea aerolinea = Aerolinea.builder()
                .id(115L)
                .nombre("Emirates")
                .vuelos(List.of())
                .build();


        when(aerolineaRepository.findByNombre("Emirates")).thenReturn(Optional.of(aerolinea));

        Optional<Aerolinea> response = aerolineaService.findAerolineaByNombre("Emirates");

        assertTrue(response.isPresent());
        assertEquals("Emirates", response.get().getNombre());
        verify(aerolineaRepository).findByNombre("Emirates");
    }

    @Test
    void aerolineaStartsWithA_Test() {
        Aerolinea aerolinea1 = Aerolinea.builder()
                .id(114L)
                .nombre("Avianca")
                .vuelos(List.of())
                .build();

        when(aerolineaRepository.aerolineaStartsWithA()).thenReturn(List.of(aerolinea1));

        List<Aerolinea> response = aerolineaService.aerolineaStartsWithA();

        for (Aerolinea aerolinea: response){
            assertTrue(aerolinea.getNombre().startsWith("A"));
        }

        assertEquals(1, response.size()); // Que no este vacia la lista.

        verify(aerolineaRepository).aerolineaStartsWithA();

    }

    @Test
    void findAerolineaByPassengerName_Test() {
    }

    @Test
    void aerolineasWithTwoFlightsx() {
    }

    @Test
    void findAllOrderedByName_Test() {
    }

    @Test
    void countAllAerolineas_Test(){

    }
}