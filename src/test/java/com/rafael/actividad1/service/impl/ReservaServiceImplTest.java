package com.rafael.actividad1.service.impl;

import com.rafael.actividad1.dto.response.PasajeroResponseDTO;
import com.rafael.actividad1.dto.response.ReservaResponseDTO;
import com.rafael.actividad1.dto.response.VueloResponseDTO;
import com.rafael.actividad1.entity.Pasajero;
import com.rafael.actividad1.entity.Reserva;
import com.rafael.actividad1.entity.Vuelo;
import com.rafael.actividad1.mapper.ReservaMapper;
import com.rafael.actividad1.repository.ReservaRepository;
import com.rafael.actividad1.service.PasajeroService;
import com.rafael.actividad1.service.VueloService;
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
class ReservaServiceImplTest {

    @Mock
    private ReservaRepository reservaRepository;

    @Mock
    private PasajeroService pasajeroService;

    @Mock
    private VueloService vueloService;

    @Mock
    ReservaMapper reservaMapper = Mappers.getMapper(ReservaMapper .class);

    @InjectMocks
    private ReservaServiceImpl reservaServiceImpl;

    public ReservaServiceImplTest() {MockitoAnnotations.openMocks(this);}

    @Test
    void findByCodigoReserva() {

        // Creamos vuelo pasajero y reserva para mockear

        UUID numVuelo = UUID.randomUUID();
        Vuelo vuelo = Vuelo.builder()
                .id(1L)
                .numeroVuelo(numVuelo)
                .origen("Santa Marta")
                .destino("Barranquilla")
                .build();

        Pasajero pasajero = Pasajero.builder()
                .nombre("Rafael Ortiz")
                .nid("P0019")
                .build();

        UUID codReserva = UUID.randomUUID();
        Reserva reserva = Reserva.builder()
                .id(1L)
                .codigoReserva(codReserva)
                .vuelo(vuelo)
                .pasajero(pasajero)
                .build();


        // Expected

        ReservaResponseDTO responseDTO = new ReservaResponseDTO(codReserva, new PasajeroResponseDTO("Rafael Ortiz", "P0019"), new VueloResponseDTO(numVuelo, "Santa Marta", "Barranquilla"));

        // Repositorio

        when(reservaRepository.findByCodigoReserva(codReserva)).thenReturn(Optional.of(reserva));

        // Mapper

        when(reservaMapper.reservaToReservaResponseDTO(reserva)).thenReturn(responseDTO);


        ReservaResponseDTO responseDTO1 = reservaServiceImpl.findByCodigoReserva(codReserva);


        assertEquals(responseDTO.codigoReserva(), responseDTO1.codigoReserva());
        assertEquals(responseDTO.vuelo().origen(), responseDTO1.vuelo().origen());

        verify(reservaRepository, times(1)).findByCodigoReserva(codReserva);
        verify(reservaMapper, times(1)).reservaToReservaResponseDTO(reserva);

    }

    @Test
    void findByPasajeroNid() {

        // Creamos vuelo pasajero y reserva para mockear

        UUID numVuelo = UUID.randomUUID();
        Vuelo vuelo = Vuelo.builder()
                .id(1L)
                .numeroVuelo(numVuelo)
                .origen("Santa Marta")
                .destino("Barranquilla")
                .build();

        String nId = "P0019";
        Pasajero pasajero = Pasajero.builder()
                .nombre("Rafael Ortiz")
                .nid(nId)
                .build();

        UUID codReserva = UUID.randomUUID();
        Reserva reserva = Reserva.builder()
                .id(1L)
                .codigoReserva(codReserva)
                .vuelo(vuelo)
                .pasajero(pasajero)
                .build();


        List<Reserva> reservas = List.of(reserva);

        // Expected

        ReservaResponseDTO responseDTO = new ReservaResponseDTO(codReserva, new PasajeroResponseDTO("Rafael Ortiz", "P0019"), new VueloResponseDTO(numVuelo, "Santa Marta", "Barranquilla"));

        // Mockear primera parte que es la llamada del servicio de pasajero

        // Expected pasajero

        PasajeroResponseDTO pasajeroResponseDTO = new PasajeroResponseDTO("Rafael Ortiz", nId);

        when(pasajeroService.findByNid(nId)).thenReturn(pasajeroResponseDTO);

        //Repositorio

        when(reservaRepository.findByPasajeroNid(nId)).thenReturn(reservas);
        // Mapper
        when(reservaMapper.reservaToReservaResponseDTO(reserva)).thenReturn(responseDTO);

        // Convierto a lista debido a que  se aplica .map(mapper::metodo)-> collectors.toList()
        List<ReservaResponseDTO> reservasDTO = List.of(responseDTO);


        // Response del servicio
        List<ReservaResponseDTO> responseDTO1 = reservaServiceImpl.findByPasajeroNid(nId);

        assertEquals(reservasDTO.size(), responseDTO1.size());
        assertEquals(reservasDTO.get(0).codigoReserva(), responseDTO1.get(0).codigoReserva());
        assertEquals(reservasDTO.get(0).vuelo().origen(), responseDTO1.get(0).vuelo().origen());

        verify(pasajeroService, times(1)).findByNid(nId);
        verify(reservaRepository, times(1)).findByPasajeroNid(nId);
        verify(reservaMapper, times(1)).reservaToReservaResponseDTO(reserva);



    }

    @Test
    void findByVueloId() {

        // Creamos vuelo pasajero y reserva para mockear

        UUID numVuelo = UUID.randomUUID();
        Long vueloId = 1L;
        Vuelo vuelo = Vuelo.builder()
                .id(vueloId)
                .numeroVuelo(numVuelo)
                .origen("Santa Marta")
                .destino("Barranquilla")
                .build();

        String nId = "P0019";
        Pasajero pasajero = Pasajero.builder()
                .nombre("Rafael Ortiz")
                .nid(nId)
                .build();

        UUID codReserva = UUID.randomUUID();
        Reserva reserva = Reserva.builder()
                .id(1L)
                .codigoReserva(codReserva)
                .vuelo(vuelo)
                .pasajero(pasajero)
                .build();


        List<Reserva> reservas = List.of(reserva);

        PasajeroResponseDTO pasajero1Response = new PasajeroResponseDTO(pasajero.getNombre(), pasajero.getNid());
        VueloResponseDTO vuelo1Response = new VueloResponseDTO(vuelo.getNumeroVuelo(), vuelo.getOrigen(), vuelo.getDestino());
        ReservaResponseDTO reserva1Response = new ReservaResponseDTO(reserva.getCodigoReserva(), pasajero1Response, vuelo1Response);

        List<ReservaResponseDTO> reservasResponse = List.of(
                reserva1Response
        );

        // Repositorio

        when(reservaRepository.findByVueloId(vueloId)).thenReturn(reservas);

        // Mapper
        when(reservaMapper.reservaToReservaResponseDTO(reserva)).thenReturn(reserva1Response);

        // Esto se convierte en una lista que es reservasResponse


        List<ReservaResponseDTO> serviceResponse  =  reservaServiceImpl.findByVueloId(vueloId);

        assertEquals(reservasResponse.size(), serviceResponse.size());
        assertEquals(reservasResponse.get(0).codigoReserva(), serviceResponse.get(0).codigoReserva());

    }

    @Test
    void existsByCodigoReserva() {

        UUID codReserva = UUID.randomUUID();
        Reserva reserva = Reserva.builder()
                .id(1L)
                .codigoReserva(codReserva)
                .build();

        boolean val = true;

        // Repositorio

        when(reservaRepository.existsByCodigoReserva(codReserva)).thenReturn(val);


        boolean response = reservaServiceImpl.existsByCodigoReserva(codReserva);

        assertTrue(response);


        verify(reservaRepository, times(1)).existsByCodigoReserva(codReserva);
    }

    @Test
    void countByVueloId() {

        // Creamos vuelo  y reserva para mockear

        UUID numVuelo = UUID.randomUUID();
        Long vueloId = 1L;
        Vuelo vuelo = Vuelo.builder()
                .id(vueloId)
                .numeroVuelo(numVuelo)
                .origen("Santa Marta")
                .destino("Barranquilla")
                .build();

        UUID codReserva = UUID.randomUUID();
        Reserva reserva = Reserva.builder()
                .id(1L)
                .codigoReserva(codReserva)
                .vuelo(vuelo)
                .build();


        List<Reserva> reservas = List.of(reserva);

        Long cantidad = 1L;

        // LLamado al servicio de vuelo

        VueloResponseDTO vueloResponseDTO = new VueloResponseDTO(numVuelo, vuelo.getOrigen(), vuelo.getDestino());
        when(vueloService.findById(vueloId)).thenReturn(vueloResponseDTO);

        // Repositorio

        when(reservaRepository.countByVueloId(vueloId)).thenReturn(cantidad);


        Long response = reservaServiceImpl.countByVueloId(vueloId);


        assertEquals(cantidad, response);
        verify(reservaRepository, times(1)).countByVueloId(vueloId);
    }

    @Test
    void findByFlightAndPassenger() {
        UUID numVuelo = UUID.randomUUID();
        Long vueloId = 1L;
        Vuelo vuelo = Vuelo.builder()
                .id(vueloId)
                .numeroVuelo(numVuelo)
                .origen("Santa Marta")
                .destino("Barranquilla")
                .build();

        String nId = "P0019";
        Long pId = 2L;
        Pasajero pasajero = Pasajero.builder()
                .id(pId)
                .nombre("Rafael Ortiz")
                .nid(nId)
                .build();

        UUID codReserva = UUID.randomUUID();
        Reserva reserva = Reserva.builder()
                .id(1L)
                .codigoReserva(codReserva)
                .vuelo(vuelo)
                .pasajero(pasajero)
                .build();

        // Respuesta esperada
        PasajeroResponseDTO pasajero1Response = new PasajeroResponseDTO(pasajero.getNombre(), pasajero.getNid());
        VueloResponseDTO vuelo1Response = new VueloResponseDTO(vuelo.getNumeroVuelo(), vuelo.getOrigen(), vuelo.getDestino());
        ReservaResponseDTO reserva1Response = new ReservaResponseDTO(reserva.getCodigoReserva(), pasajero1Response, vuelo1Response);


        when(reservaRepository.findByFlightAndPassenger(vueloId, pasajero.getId())).thenReturn(Optional.of(reserva));
        when(reservaMapper.reservaToReservaResponseDTO(reserva)).thenReturn(reserva1Response);


        ReservaResponseDTO responseDTO = reservaServiceImpl.findByFlightAndPassenger(vueloId, pId);

        assertEquals(reserva1Response.codigoReserva(), responseDTO.codigoReserva());
        verify(reservaRepository, times(1)).findByFlightAndPassenger(vueloId, pId);

    }

    @Test
    void findByPasajeroNombre() {

        UUID numVuelo = UUID.randomUUID();
        Long vueloId = 1L;
        Vuelo vuelo = Vuelo.builder()
                .id(vueloId)
                .numeroVuelo(numVuelo)
                .origen("Santa Marta")
                .destino("Barranquilla")
                .build();

        String nId = "P0019";
        Long pId = 2L;
        Pasajero pasajero = Pasajero.builder()
                .id(pId)
                .nombre("Rafael Ortiz")
                .nid(nId)
                .build();

        UUID codReserva = UUID.randomUUID();
        Reserva reserva = Reserva.builder()
                .id(1L)
                .codigoReserva(codReserva)
                .vuelo(vuelo)
                .pasajero(pasajero)
                .build();

        PasajeroResponseDTO pasajero1Response = new PasajeroResponseDTO(pasajero.getNombre(), pasajero.getNid());
        VueloResponseDTO vuelo1Response = new VueloResponseDTO(vuelo.getNumeroVuelo(), vuelo.getOrigen(), vuelo.getDestino());
        ReservaResponseDTO reserva1Response = new ReservaResponseDTO(reserva.getCodigoReserva(), pasajero1Response, vuelo1Response);

        when(reservaRepository.findByPasajeroNombre("Rafael Ortiz")).thenReturn(List.of(reserva));
        when(reservaMapper.reservaToReservaResponseDTO(reserva)).thenReturn(reserva1Response);

        List<ReservaResponseDTO> reservaResponseDTOS = List.of(
                reserva1Response
        );


        List<ReservaResponseDTO> responseDTO = reservaServiceImpl.findByPasajeroNombre("Rafael Ortiz");

        assertEquals(reservaResponseDTOS.size(), responseDTO.size());
        assertEquals(reservaResponseDTOS.get(0).codigoReserva(), responseDTO.get(0).codigoReserva());
        verify(reservaRepository, times(1)).findByPasajeroNombre("Rafael Ortiz");

    }

    @Test
    void existsByFlightId() {

        UUID numVuelo = UUID.randomUUID();
        Long vueloId = 1L;
        Vuelo vuelo = Vuelo.builder()
                .id(vueloId)
                .numeroVuelo(numVuelo)
                .origen("Santa Marta")
                .destino("Barranquilla")
                .build();

        UUID codReserva = UUID.randomUUID();
        Reserva reserva = Reserva.builder()
                .id(1L)
                .codigoReserva(codReserva)
                .vuelo(vuelo)
                .build();



        boolean val = true;

        // Repositorio

        when(reservaRepository.existsByFlightId(vueloId)).thenReturn(val);


        boolean response = reservaServiceImpl.existsByFlightId(vueloId);

        assertTrue(response);

        verify(reservaRepository, times(1)).existsByFlightId(vueloId);
    }

    @Test
    void deleteByCodigo() {


        UUID codReserva = UUID.randomUUID();
        Reserva reserva = Reserva.builder()
                .id(1L)
                .codigoReserva(codReserva)
                .build();



        int val = 1;

        // Se verifica en el servicio que exista la reserva
        when(reservaRepository.existsByCodigoReserva(codReserva)).thenReturn(true);
        // Repositorio
        when(reservaRepository.deleteByCodigo(codReserva)).thenReturn(val);

        int response = reservaServiceImpl.deleteByCodigo(codReserva);


        assertEquals(val, response);

        verify(reservaRepository, times(1)).deleteByCodigo(codReserva);


    }
}