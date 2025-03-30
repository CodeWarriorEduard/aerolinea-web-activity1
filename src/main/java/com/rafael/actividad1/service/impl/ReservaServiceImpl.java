package com.rafael.actividad1.service.impl;

import com.rafael.actividad1.dto.response.ReservaResponseDTO;
import com.rafael.actividad1.entity.Reserva;
import com.rafael.actividad1.exceptions.ReservaNotFoundException;
import com.rafael.actividad1.mapper.ReservaMapper;
import com.rafael.actividad1.repository.ReservaRepository;
import com.rafael.actividad1.service.ReservaService;
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

    @Autowired
    public ReservaServiceImpl(ReservaRepository reservaRepository, ReservaMapper reservaMapper) {
        this.reservaRepository = reservaRepository;
        this.reservaMapper = reservaMapper;
    }

    @Override
    public ReservaResponseDTO findByCodigoReserva(UUID codigoReserva) {
        Reserva reserva = reservaRepository.findByCodigoReserva(codigoReserva).orElseThrow(() -> new ReservaNotFoundException("No existe una reserva con el codigo: " + codigoReserva));
        return reservaMapper.reservaToReservaResponsetDTO(reserva);
    }

    @Override
    public List<ReservaResponseDTO> findByPasajeroId(Long pasajeroId) {
        List<Reserva> reservas = reservaRepository.findByPasajeroId(pasajeroId);
        return reservas.stream()
                .map(reservaMapper::reservaToReservaResponsetDTO)
                .collect(Collectors.toList());
    }

    @Override
    public List<ReservaResponseDTO> findByVueloId(Long vueloId) {
        List<Reserva> reservas = reservaRepository.findByVueloId(vueloId);
        return reservas.stream()
                .map(reservaMapper::reservaToReservaResponsetDTO)
                .collect(Collectors.toList());
    }

    @Override
    public boolean existsByCodigoReserva(UUID codigoReserva) {
        return reservaRepository.existsByCodigoReserva(codigoReserva);
    }

    @Override
    public int countByVueloId(Long vueloId) {
        return reservaRepository.countByVueloId(vueloId);
    }

    @Override
    public List<ReservaResponseDTO> findByPasajeroNid(String nId) {
        List<Reserva> reservas = reservaRepository.findByPasajeroNid(nId);
        return reservas.stream()
                .map(reservaMapper::reservaToReservaResponsetDTO)
                .collect(Collectors.toList());
    }

    @Override
    public ReservaResponseDTO findByFlightAndPassenger(Long flightId, Long passengerId) {
        Reserva reserva = reservaRepository.findByFlightAndPassenger(flightId,passengerId).orElseThrow(() -> new ReservaNotFoundException("No existe una reserva con ese vuelo y/o pasajero"));
        return reservaMapper.reservaToReservaResponsetDTO(reserva);

    }

    @Override
    public List<ReservaResponseDTO> findByPasajeroNombre(String passengerName) {
        List<Reserva> reservas = reservaRepository.findByPasajeroNombre(passengerName);
        return reservas.stream()
                .map(reservaMapper::reservaToReservaResponsetDTO)
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
