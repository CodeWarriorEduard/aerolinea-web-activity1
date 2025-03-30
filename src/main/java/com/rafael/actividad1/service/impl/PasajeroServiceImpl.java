package com.rafael.actividad1.service.impl;

import com.rafael.actividad1.dto.response.PasajeroResponseDTO;
import com.rafael.actividad1.entity.Pasajero;
import com.rafael.actividad1.exceptions.PasajeroNotFoundException;
import com.rafael.actividad1.mapper.PasajeroMapper;
import com.rafael.actividad1.repository.PasajeroRepository;
import com.rafael.actividad1.service.PasajeroService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class PasajeroServiceImpl implements PasajeroService {


    private final PasajeroRepository pasajeroRepository;

    private final PasajeroMapper pasajeroMapper;
    @Autowired
    public PasajeroServiceImpl(PasajeroRepository pasajeroRepository, PasajeroMapper pasajeroMapper) {
        this.pasajeroRepository = pasajeroRepository;
        this.pasajeroMapper = pasajeroMapper;
    }

    @Override
    public PasajeroResponseDTO findByNombre(String nombre) {
        Pasajero pasajero = pasajeroRepository.findByNombre(nombre).orElseThrow(()->new PasajeroNotFoundException("No se encontro un pasajero con el nombre: " + nombre));
        return pasajeroMapper.pasajeroToPasajeroResponseDTO(pasajero);
    }

    @Override
    public PasajeroResponseDTO findByNid(String nId) {
        Pasajero pasajero = pasajeroRepository.findByNid(nId).orElseThrow(()->new PasajeroNotFoundException("No se encontro el pasajero con el nId: " + nId));
        return pasajeroMapper.pasajeroToPasajeroResponseDTO(pasajero);
    }

    @Override
    public List<PasajeroResponseDTO> findByNombreContaining(String partialName) {
        List<Pasajero> pasajeros = pasajeroRepository.findByNombreContaining(partialName);
        return pasajeros.stream()
                .map(pasajeroMapper::pasajeroToPasajeroResponseDTO)
                .collect(Collectors.toList());
    }

    @Override
    public boolean existsByNid(String nId) {
        return pasajeroRepository.existsByNid(nId);
    }

    @Override
    public int countByNombre(String nombre) {
        return pasajeroRepository.countByNombre(nombre);
    }

    @Override
    public PasajeroResponseDTO findByPasaporteNumero(String passportNumber) {
        Pasajero pasajero = pasajeroRepository.findByPasaporteNumero(passportNumber).orElseThrow(()->new PasajeroNotFoundException("No se encontro un pasajero con el numero de pasaporte: " + passportNumber));
        return pasajeroMapper.pasajeroToPasajeroResponseDTO(pasajero);
    }

    @Override
    public List<PasajeroResponseDTO> searchByPartialName(String partialName) {
        List<Pasajero> pasajeros = pasajeroRepository.searchByPartialName(partialName);
        return pasajeros.stream()
                .map(pasajeroMapper::pasajeroToPasajeroResponseDTO)
                .collect(Collectors.toList());
    }

    @Override
    public boolean existsWithPasaporte() {
        return pasajeroRepository.existsWithPasaporte();
    }

    @Override
    public PasajeroResponseDTO findByNidAndNombre(String nid, String name) {
        Pasajero pasajero = pasajeroRepository.findByNidAndNombre(nid,name).orElseThrow(()->new PasajeroNotFoundException("No se encontro el pasajero con el id: " + nid + " y con el nombre: " + name));
        return pasajeroMapper.pasajeroToPasajeroResponseDTO(pasajero);
    }

    @Override
    public boolean deleteByNId(String nId) {
        boolean succes = false;

        Optional<Pasajero> pasajero = pasajeroRepository.findByNid(nId);
        if (pasajero.isPresent()) {
            pasajeroRepository.deleteByNId(nId);
            succes = true;
        }
        return succes;
    }
}
