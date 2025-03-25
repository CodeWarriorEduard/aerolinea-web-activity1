package com.rafael.actividad1.service;

import com.rafael.actividad1.entity.Aerolinea;
import com.rafael.actividad1.entity.Pasajero;
import com.rafael.actividad1.entity.Reserva;
import com.rafael.actividad1.entity.Vuelo;
import com.rafael.actividad1.repository.AerolineaRepository;
import com.rafael.actividad1.service.impl.AerolineaServiceImpl;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

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

        Pasajero pasajero = Pasajero.builder()
                .id(1L)
                .nombre("Rafael Ortiz")
                .nid("P0589")
                .pasaporte(null)
                .build();

        Aerolinea aerolinea1 = Aerolinea.builder()
                .id(114L)
                .nombre("Avianca")
                .vuelos(List.of())
                .build();

        List<Aerolinea> aerolineas = List.of(aerolinea1);

        Vuelo vuelo = Vuelo.builder()
                .numeroVuelo(UUID.randomUUID())
                .origen("Santa Marta")
                .destino("Bogotá")
                .aerolineas(aerolineas)
                .build();

        Reserva reserva = Reserva.builder()
                .id(1L)
                .pasajero(pasajero)
                .vuelo(vuelo)
                .codigoReserva(UUID.randomUUID())
                .build();

        when(aerolineaRepository.findAerolineaByPassengerName("Rafael Ortiz")).thenReturn(List.of(aerolinea1));

        List<Aerolinea> response = aerolineaService.findAerolineaByPassengerName("Rafael Ortiz");

        assertEquals(List.of(aerolinea1), response);
        assertEquals(1, response.size());
        assertEquals("Avianca", reserva.getVuelo().getAerolineas().get(0).getNombre());

        verify(aerolineaRepository, times(1)).findAerolineaByPassengerName("Rafael Ortiz");

    }

    @Test
    void aerolineasWithTwoFlightsx() {

        Vuelo vuelo1 = Vuelo.builder()
                .numeroVuelo(UUID.randomUUID())
                .origen("Santa Marta")
                .destino("Bogotá")
                .build();

        Vuelo vuelo2 = Vuelo.builder()
                .numeroVuelo(UUID.randomUUID())
                .origen("Santa Marta")
                .destino("Barranquilla")
                .build();

        List<Vuelo> vuelos = List.of(
                vuelo1,
                vuelo2
        );

        Aerolinea aerolinea1 = Aerolinea.builder()
                .id(114L)
                .nombre("Avianca")
                .vuelos(vuelos)
                .build();

        when(aerolineaRepository.aerolineasWithTwoFlightsx()).thenReturn(List.of(aerolinea1));

        List<Aerolinea> response = aerolineaService.aerolineasWithTwoFlightsx();

        assertEquals(1, response.size());
        assertEquals("Santa Marta", response.get(0).getVuelos().get(0).getOrigen());
        assertEquals("Barranquilla", response.get(0).getVuelos().get(1).getDestino());

        verify(aerolineaRepository, times(1)).aerolineasWithTwoFlightsx();

    }

    @Test
    void findAllOrderedByName_Test() {

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
                aerolinea2,
                aerolinea1

        );

        when(aerolineaRepository.findAllOrderedByName()).thenReturn(List.of(aerolinea1, aerolinea2));

        List<Aerolinea> response = aerolineaService.findAllOrderedByName();

        assertEquals(2, response.size());

        assertEquals(aerolineas.get(0).getNombre(), response.get(1).getNombre());
        assertEquals(aerolineas.get(1).getNombre(), response.get(0).getNombre());

        verify(aerolineaRepository, times(1)).findAllOrderedByName();

    }

    @Test
    void countAllAerolineasTest(){
//        Aerolinea aerolinea1 = Aerolinea.builder()
//                .id(114L)
//                .nombre("Avianca")
//                .vuelos(List.of())
//                .build();
//
//        Aerolinea aerolinea2 = Aerolinea.builder()
//                .id(115L)
//                .nombre("Emirates")
//                .vuelos(List.of())
//                .build();
//
//        Aerolinea aerolinea3 = Aerolinea.builder()
//                .id(116L)
//                .nombre("Delta Airlines")
//                .vuelos(List.of())
//                .build();
//
//        Aerolinea aerolinea4 = Aerolinea.builder()
//                .id(117L)
//                .nombre("LATAM")
//                .vuelos(List.of())
//                .build();
//
//        Aerolinea aerolinea5 = Aerolinea.builder()
//                .id(118L)
//                .nombre("American Airlines")
//                .vuelos(List.of())
//                .build();
//
//        Aerolinea aerolinea6 = Aerolinea.builder()
//                .id(119L)
//                .nombre("Turkish Airlines")
//                .vuelos(List.of())
//                .build();
//
//        Aerolinea aerolinea7 = Aerolinea.builder()
//                .id(120L)
//                .nombre("Qatar Airways")
//                .vuelos(List.of())
//                .build();
//
//        List<Aerolinea> aerolineas = List.of(
//                aerolinea2,
//                aerolinea1,
//                aerolinea4,
//                aerolinea3,
//                aerolinea7,
//                aerolinea5,
//                aerolinea6
//        );

        //XD

        when(aerolineaRepository.countAllAerolineas()).thenReturn(7L);

        Long count = aerolineaService.countAllAerolineas();

        assertEquals(7L, count);
        verify(aerolineaRepository, times(1)).countAllAerolineas();

    }
}