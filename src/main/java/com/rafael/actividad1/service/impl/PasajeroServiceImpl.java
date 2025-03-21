package com.rafael.actividad1.service.impl;

import com.rafael.actividad1.dto.pasajero.PasajeroDTO;
import com.rafael.actividad1.entity.Pasajero;
import com.rafael.actividad1.exceptions.ResourceNotFoundException;
import com.rafael.actividad1.mapper.PasajeroMapper;
import com.rafael.actividad1.repository.AerolineaRepository;
import com.rafael.actividad1.repository.PasajeroRepository;
import com.rafael.actividad1.service.PasajeroService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class PasajeroServiceImpl implements PasajeroService {


    private PasajeroRepository pasajeroRepository;

    private PasajeroMapper pasajeroMapper;
    @Autowired
    public PasajeroServiceImpl(PasajeroRepository pasajeroRepository, PasajeroMapper pasajeroMapper) {
        this.pasajeroRepository = pasajeroRepository;
        this.pasajeroMapper = pasajeroMapper;
    }

    @Override
    public PasajeroDTO findByNombre(String nombre) {
        Pasajero pasajero = pasajeroRepository.findByNombre(nombre).orElseThrow(()->new ResourceNotFoundException("No se encontro el pasajero"));
        return pasajeroMapper.pasajeroToPasajeroDTO(pasajero);
    }

    @Override
    public PasajeroDTO findByNid(String nId) {
        Pasajero pasajero = pasajeroRepository.findByNid(nId).orElseThrow(()->new ResourceNotFoundException("No se encontro el pasajero"));
        return pasajeroMapper.pasajeroToPasajeroDTO(pasajero);
    }

    @Override
    public List<PasajeroDTO> findByNombreContaining(String partialName) {
        List<Pasajero> pasajeros = pasajeroRepository.findByNombreContaining(partialName);
        if (pasajeros.isEmpty()) {
            throw new ResourceNotFoundException("No se encontraron pasajeros con ese nombre");
        }
        return pasajeros.stream()
                .map(pasajeroMapper::pasajeroToPasajeroDTO)
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
    public PasajeroDTO findByPasaporteNumero(String passportNumber) {
        Pasajero pasajero = pasajeroRepository.findByPasaporteNumero(passportNumber).orElseThrow(()->new ResourceNotFoundException("No se encontro el pasajero"));
        return pasajeroMapper.pasajeroToPasajeroDTO(pasajero);
    }

    @Override
    public List<PasajeroDTO> searchByPartialName(String partialName) {
        List<Pasajero> pasajeros = pasajeroRepository.searchByPartialName(partialName);
        if (pasajeros.isEmpty()) {
            throw new ResourceNotFoundException("No se encontraron pasajeros con ese nombre");
        }
        return pasajeros.stream()
                .map(pasajeroMapper::pasajeroToPasajeroDTO)
                .collect(Collectors.toList());
    }

    @Override
    public boolean existsWithPasaporte() {
        return pasajeroRepository.existsWithPasaporte();
    }

    @Override
    public PasajeroDTO findByNidAndNombre(String nid, String name) {
        Pasajero pasajero = pasajeroRepository.findByNidAndNombre(nid,name).orElseThrow(()->new ResourceNotFoundException("No se encontro el pasajero"));
        return pasajeroMapper.pasajeroToPasajeroDTO(pasajero);
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
