package com.rafael.actividad1.service.impl;

import com.rafael.actividad1.dto.response.PasajeroResponseDTO;
import com.rafael.actividad1.dto.response.ReservaResponseDTO;
import com.rafael.actividad1.dto.response.VueloResponseDTO;
import com.rafael.actividad1.entity.Reserva;
import com.rafael.actividad1.exceptions.PasajeroNotFoundException;
import com.rafael.actividad1.exceptions.ReservaNotFoundException;
import com.rafael.actividad1.exceptions.VueloNotFoundException;
import com.rafael.actividad1.mapper.ReservaMapper;
import com.rafael.actividad1.repository.ReservaRepository;
import com.rafael.actividad1.service.PasajeroService;
import com.rafael.actividad1.service.ReservaService;
import com.rafael.actividad1.service.VueloService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class ReservaServiceImpl implements ReservaService {


    private final ReservaRepository reservaRepository;


    private final ReservaMapper reservaMapper;


    // Para relaciones

    private final PasajeroService pasajeroService;
    private final VueloService vueloService;


    @Autowired
    public ReservaServiceImpl(ReservaRepository reservaRepository, ReservaMapper reservaMapper, PasajeroService pasajeroService, VueloService vueloService) {
        this.reservaRepository = reservaRepository;
        this.reservaMapper = reservaMapper;
        this.pasajeroService = pasajeroService;
        this.vueloService = vueloService;
    }



    @Override
    public ReservaResponseDTO findByCodigoReserva(UUID codigoReserva) {
        Reserva reserva = reservaRepository.findByCodigoReserva(codigoReserva).orElseThrow(() -> new ReservaNotFoundException("No existe una reserva con el codigo: " + codigoReserva));
        return reservaMapper.reservaToReservaResponseDTO(reserva);
    }

    @Override
    public List<ReservaResponseDTO> findByPasajeroNid(String nId) {

        PasajeroResponseDTO pasajeroResponseDTO = Optional.ofNullable(pasajeroService.findByNid(nId))
                .orElseThrow(()-> new PasajeroNotFoundException("No existe el pasajero"));


        List<Reserva> reservas = reservaRepository.findByPasajeroNid(pasajeroResponseDTO.nid());
        return reservas.stream()
                .map(reservaMapper::reservaToReservaResponseDTO)
                .collect(Collectors.toList());
    }

    @Override
    public List<ReservaResponseDTO> findByVueloId(Long vueloId) {
        List<Reserva> reservas = reservaRepository.findByVueloId(vueloId);
        return reservas.stream()
                .map(reservaMapper::reservaToReservaResponseDTO)
                .collect(Collectors.toList());
    }

    @Override
    public boolean existsByCodigoReserva(UUID codigoReserva) {
        return reservaRepository.existsByCodigoReserva(codigoReserva);
    }

    @Override
    public Long countByVueloId(Long vueloId) {

        VueloResponseDTO response = Optional.ofNullable(vueloService.findById(vueloId))
                .orElseThrow(()-> new VueloNotFoundException("Vuelo con id: "+vueloId+" No encontrado"));

        return reservaRepository.countByVueloId(vueloId);
    }


    @Override
    public ReservaResponseDTO findByFlightAndPassenger(Long flightId, Long passengerId) {
        Reserva reserva = reservaRepository.findByFlightAndPassenger(flightId,passengerId).orElseThrow(() -> new ReservaNotFoundException("No existe una reserva con ese vuelo y/o pasajero"));
        return reservaMapper.reservaToReservaResponseDTO(reserva);

    }

    @Override
    public List<ReservaResponseDTO> findByPasajeroNombre(String passengerName) {

        List<PasajeroResponseDTO> pasajeroResponseDTO = Optional.ofNullable(pasajeroService.findByNombre(passengerName))
                .orElseThrow(() -> new PasajeroNotFoundException("Pasajeros con nombre "+passengerName+" no encotrados"));

        List<Reserva> reservas = reservaRepository.findByPasajeroNombre(passengerName);
        return reservas.stream()
                .map(reservaMapper::reservaToReservaResponseDTO)
                .collect(Collectors.toList());
    }

    @Override
    public boolean existsByFlightId(Long flightId) {
        return reservaRepository.existsByFlightId(flightId);
    }

    @Override
    public int deleteByCodigo(UUID code) {

        if (!reservaRepository.existsByCodigoReserva(code)){
            throw new ReservaNotFoundException("No se encontro la reserva con el codigo: " + code);
        }

        return reservaRepository.deleteByCodigo(code);
    }
}
