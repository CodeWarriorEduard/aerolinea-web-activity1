package com.rafael.actividad1.service.impl;

import com.rafael.actividad1.dto.response.PasaporteResponseDTO;
import com.rafael.actividad1.entity.Pasaporte;
import com.rafael.actividad1.exceptions.PasaporteNotFoundException;
import com.rafael.actividad1.mapper.PasaporteMapper;
import com.rafael.actividad1.repository.PasaporteRepository;
import com.rafael.actividad1.service.PasaporteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PasaporteServiceImpl implements PasaporteService {

    private final PasaporteRepository pasaporteRepository;
    private final PasaporteMapper pasaporteMapper;

    @Autowired
    public PasaporteServiceImpl(PasaporteRepository pasaporteRepository, PasaporteMapper pasaporteMapper) {
        this.pasaporteRepository = pasaporteRepository;
        this.pasaporteMapper = pasaporteMapper;
    }

    @Override
    public PasaporteResponseDTO findByNumero(String numero) {
        Pasaporte pasaporte = pasaporteRepository.findByNumero(numero).orElseThrow(() -> new PasaporteNotFoundException("No se encontro el pasaporte con el numero " + numero));

        return pasaporteMapper.pasaporteToPasaporteDTO(pasaporte);
    }

    @Override
    public PasaporteResponseDTO findByPasajeroId(Long pasajeroId) {
        Pasaporte pasaporte = pasaporteRepository.findByPasajeroId(pasajeroId).orElseThrow(() -> new PasaporteNotFoundException("No se encontro el pasaporte con ese pasajero"));

        return pasaporteMapper.pasaporteToPasaporteDTO(pasaporte);
    }

    @Override
    public boolean existsByNumero(String numero) {
        return pasaporteRepository.existsByNumero(numero);
    }

    @Override
    public PasaporteResponseDTO findByPasajeroNombre(String nombre) {
        Pasaporte pasaporte = pasaporteRepository.findByPasajeroNombre(nombre).orElseThrow(() -> new PasaporteNotFoundException("No se encontro el pasaporte con ese pasajero"));

        return pasaporteMapper.pasaporteToPasaporteDTO(pasaporte);
    }

    @Override
    public int deleteByNumero(String numero) {

        if(!pasaporteRepository.existsByNumero(numero)) {
            throw new PasaporteNotFoundException("No se encontro el pasaporte con el numero " + numero);
        }

        return pasaporteRepository.deleteByNumero(numero);
    }

    @Override
    public PasaporteResponseDTO findByPasajeroNId(String nId) {
        Pasaporte pasaporte = pasaporteRepository.findByPasajeroNId(nId).orElseThrow(() -> new PasaporteNotFoundException("No se encontro el pasaporte con ese pasajero"));

        return pasaporteMapper.pasaporteToPasaporteDTO(pasaporte);
    }

    @Override
    public List<PasaporteResponseDTO> searchByPartialNumber(String partialNumber) {
        List<Pasaporte> pasaportes = pasaporteRepository.searchByPartialNumber(partialNumber);
        return pasaportes.stream()
                .map(pasaporteMapper::pasaporteToPasaporteDTO)
                .collect(Collectors.toList());
    }

    @Override
    public PasaporteResponseDTO findByPasajeroNombreAndNid(String name, String nid) {
        Pasaporte pasaporte = pasaporteRepository.findByPasajeroNombreAndNid(name,nid).orElseThrow(() -> new PasaporteNotFoundException("No se encontro el pasaporte con ese pasajero"));

        return pasaporteMapper.pasaporteToPasaporteDTO(pasaporte);
    }

    @Override
    public boolean existsByPasajeroNid(String passengerNid) {
        return pasaporteRepository.existsByPasajeroNid(passengerNid);
    }

    @Override
    public int deleteByPasajeroNid(String passengerNid) {

        if (!pasaporteRepository.existsByPasajeroNid(passengerNid)) {
            throw new PasaporteNotFoundException("No existe el pasaporte con ese pasajero");
        }

        return pasaporteRepository.deleteByPasajeroNid(passengerNid);
    }
}
