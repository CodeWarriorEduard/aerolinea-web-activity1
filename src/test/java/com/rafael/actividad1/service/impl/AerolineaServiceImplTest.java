package com.rafael.actividad1.service.impl;

import com.rafael.actividad1.dto.request.AerolineaRequestDTO;
import com.rafael.actividad1.dto.response.AerolineaResponseDTO;
import com.rafael.actividad1.dto.response.AerolineaVuelosResponseDTO;
import com.rafael.actividad1.dto.response.VueloResponseDTO;
import com.rafael.actividad1.entity.Aerolinea;
import com.rafael.actividad1.entity.Pasajero;
import com.rafael.actividad1.entity.Reserva;
import com.rafael.actividad1.entity.Vuelo;
import com.rafael.actividad1.mapper.AerolineaMapper;
import com.rafael.actividad1.repository.AerolineaRepository;
import org.junit.jupiter.api.Test;
import org.mapstruct.factory.Mappers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;


class AerolineaServiceImplTest {

    @Mock
    private AerolineaRepository aerolineaRepository;

    @Mock
    AerolineaMapper aerolineaMapper = Mappers.getMapper(AerolineaMapper .class);

    @InjectMocks
    private AerolineaServiceImpl aerolineaService;



    public AerolineaServiceImplTest() {
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
                .id(id)
                .nombre("Avianca")
                .vuelos(List.of())
                .build();

        AerolineaResponseDTO responseDTO = new AerolineaResponseDTO("Avianca");


        when(aerolineaRepository.findById(id)).thenReturn(Optional.of(aerolinea));
        when(aerolineaMapper.aerolineaResponseDto(aerolinea)).thenReturn(responseDTO);


        AerolineaResponseDTO response = aerolineaService.findAerolineaById(id);

        assertEquals(responseDTO.nombre(), response.nombre());

        verify(aerolineaRepository, times(1)).findById(id);
        verify(aerolineaMapper, times(1)).aerolineaResponseDto(aerolinea);
    }

    @Test
    void saveAerolinea_Test(){

        Aerolinea aerolinea = Aerolinea.builder()
                .id(114L)
                .nombre("Avianca")
                .vuelos(List.of())
                .build();

        AerolineaResponseDTO expected = new AerolineaResponseDTO("Avianca");

        AerolineaRequestDTO aerolineaRequestDTO = new AerolineaRequestDTO("Avianca",List.of());

        when(aerolineaRepository.save(aerolinea)).thenReturn(aerolinea);

        when(aerolineaMapper.aerolineaResponseDto(aerolinea)).thenReturn(expected);

        when(aerolineaMapper.aerolineaRequestDtoToAerolinea(aerolineaRequestDTO)).thenReturn(aerolinea);

        AerolineaResponseDTO response = aerolineaService.saveAerolinea(aerolineaRequestDTO);

        assertEquals(expected.nombre(), response.nombre());
        verify(aerolineaRepository).save(aerolinea);
        verify(aerolineaMapper, times(1)).aerolineaResponseDto(aerolinea);

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


        AerolineaResponseDTO responseDTO1 = new AerolineaResponseDTO("Avianca");
        AerolineaResponseDTO responseDTO2 = new AerolineaResponseDTO("Emirates");

        List<AerolineaResponseDTO> aerolineaResponseDTOS = List.of(responseDTO1, responseDTO2);

        when(aerolineaRepository.findAll()).thenReturn(aerolineas);
        when(aerolineaMapper.toListOfAerolineaResponseDTO(aerolineas)).thenReturn(aerolineaResponseDTOS);

        List<AerolineaResponseDTO> response = aerolineaService.findAllAerolineasInDb();

        assertEquals(aerolineaResponseDTOS, response);

        verify(aerolineaRepository).findAll();
        verify(aerolineaMapper, times(1)).toListOfAerolineaResponseDTO(aerolineas);
    }


    @Test
    void findAerolineaByNombre_Test(){

        Aerolinea aerolinea = Aerolinea.builder()
                .id(115L)
                .nombre("Emirates")
                .vuelos(List.of())
                .build();

        AerolineaResponseDTO aerolineaResponseDTO =new AerolineaResponseDTO("Emirates");

        when(aerolineaRepository.findByNombre("Emirates")).thenReturn(Optional.of(aerolinea));
        when(aerolineaMapper.aerolineaResponseDto(aerolinea)).thenReturn(aerolineaResponseDTO);

        Optional<AerolineaResponseDTO> response = aerolineaService.findAerolineaByNombre("Emirates");

        assertTrue(response.isPresent());
        assertEquals(aerolineaResponseDTO.nombre(), response.get().nombre());
        verify(aerolineaRepository).findByNombre("Emirates");
    }

    @Test
    void aerolineaStartsWithA_Test() {
        Aerolinea aerolinea1 = Aerolinea.builder()
                .id(114L)
                .nombre("Avianca")
                .vuelos(List.of())
                .build();

        List<Aerolinea> aerolineas = List.of(aerolinea1);

        List<AerolineaResponseDTO> aerolineaResponseDTOS = List.of(
                new AerolineaResponseDTO("Avianca")
        );

        when(aerolineaRepository.aerolineaStartsWithA()).thenReturn(aerolineas);

        when(aerolineaMapper.toListOfAerolineaResponseDTO(aerolineas)).thenReturn(aerolineaResponseDTOS);

        List<AerolineaResponseDTO> response = aerolineaService.aerolineaStartsWithA();


        for (AerolineaResponseDTO aerolinea: response){
            assertTrue(aerolinea.nombre().startsWith("A"));
        }

        assertEquals(1, response.size()); // Verificar que no este vacia la lista.
        assertEquals(aerolineaResponseDTOS, response);
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


        List<AerolineaResponseDTO> aerolineaResponseDTOS = List.of(
                new AerolineaResponseDTO("Avianca")
        );

        when(aerolineaRepository.findAerolineaByPassengerName("Rafael Ortiz")).thenReturn(List.of(aerolinea1));
        when(aerolineaMapper.toListOfAerolineaResponseDTO(aerolineas)).thenReturn(aerolineaResponseDTOS);
        List<AerolineaResponseDTO> response = aerolineaService.findAerolineaByPassengerName("Rafael Ortiz");

        assertEquals(aerolineaResponseDTOS, response);
        assertEquals(1, response.size());
        assertEquals("Avianca", reserva.getVuelo().getAerolineas().get(0).getNombre());

        verify(aerolineaRepository, times(1)).findAerolineaByPassengerName("Rafael Ortiz");

    }

    @Test
    void aerolineasWithTwoFlightsx() {

        UUID nVuelo1 = UUID.randomUUID();
        UUID nVuelo2 = UUID.randomUUID();

        Vuelo vuelo1 = Vuelo.builder()
                .numeroVuelo(nVuelo1)
                .origen("Santa Marta")
                .destino("Bogotá")
                .build();

        Vuelo vuelo2 = Vuelo.builder()
                .numeroVuelo(nVuelo2)
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

        // Expected

        // Lista de vuelos

        List<VueloResponseDTO> vuelosDTO = List.of(
                new VueloResponseDTO(nVuelo1, "Santa Marta", "Bogotá"),
                new VueloResponseDTO(nVuelo2, "Santa Marta", "Barranquilla")
        );

        List<AerolineaVuelosResponseDTO> aerolineaResponseDTOS = List.of(
                new AerolineaVuelosResponseDTO("Avianca", vuelosDTO));

        when(aerolineaRepository.aerolineasWithTwoFlightsx()).thenReturn(List.of(aerolinea1));
        when(aerolineaMapper.aerolineaVuelosResponseDtos(List.of(aerolinea1))).thenReturn(aerolineaResponseDTOS);
        List<AerolineaVuelosResponseDTO> response = aerolineaService.aerolineasWithTwoFlightsx();

        assertEquals(aerolineaResponseDTOS.size(), response.size());
        assertEquals("Santa Marta", response.get(0).vuelos().get(0).origen());
        assertEquals("Barranquilla", response.get(0).vuelos().get(1).destino());

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

        List<Aerolinea> aerolineas = List.of(aerolinea1, aerolinea2);

        List<AerolineaResponseDTO> responseDTO = List.of(
                new AerolineaResponseDTO("Avianca"),
                new AerolineaResponseDTO("Emirates")
        );

        when(aerolineaRepository.findAllOrderedByName()).thenReturn(aerolineas);
        when(aerolineaMapper.toListOfAerolineaResponseDTO(aerolineas)).thenReturn(responseDTO);

        List<AerolineaResponseDTO> response = aerolineaService.findAllOrderedByName();

        assertEquals(responseDTO.size(), response.size());

        assertEquals(responseDTO.get(0).nombre(), response.get(0).nombre());
        assertEquals(responseDTO.get(1).nombre(), response.get(1).nombre());

        verify(aerolineaRepository, times(1)).findAllOrderedByName();
        verify(aerolineaMapper, times(1)).toListOfAerolineaResponseDTO(aerolineas);


    }

    @Test
    void countAllAerolineasTest(){
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

        Aerolinea aerolinea3 = Aerolinea.builder()
                .id(116L)
                .nombre("Delta Airlines")
                .vuelos(List.of())
                .build();

        Aerolinea aerolinea4 = Aerolinea.builder()
                .id(117L)
                .nombre("LATAM")
                .vuelos(List.of())
                .build();

        Aerolinea aerolinea5 = Aerolinea.builder()
                .id(118L)
                .nombre("American Airlines")
                .vuelos(List.of())
                .build();

        Aerolinea aerolinea6 = Aerolinea.builder()
                .id(119L)
                .nombre("Turkish Airlines")
                .vuelos(List.of())
                .build();

        Aerolinea aerolinea7 = Aerolinea.builder()
                .id(120L)
                .nombre("Qatar Airways")
                .vuelos(List.of())
                .build();

        List<Aerolinea> aerolineas = List.of(
                aerolinea2,
                aerolinea1,
                aerolinea4,
                aerolinea3,
                aerolinea7,
                aerolinea5,
                aerolinea6
        );


        when(aerolineaRepository.countAllAerolineas()).thenReturn(7L);

        Long count = aerolineaService.countAllAerolineas();

        System.out.println(count);

        assertEquals(aerolineas.size(), count);
        verify(aerolineaRepository, times(1)).countAllAerolineas();

    }
}