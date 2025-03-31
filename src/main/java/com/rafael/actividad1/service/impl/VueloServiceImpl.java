package com.rafael.actividad1.service.impl;

import com.rafael.actividad1.dto.request.VueloRequestDTO;
import com.rafael.actividad1.dto.response.VueloResponseDTO;
import com.rafael.actividad1.entity.Vuelo;
import com.rafael.actividad1.exceptions.VueloNotFoundException;
import com.rafael.actividad1.mapper.VueloMapper;
import com.rafael.actividad1.repository.VueloRepository;
import com.rafael.actividad1.service.VueloService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class VueloServiceImpl implements VueloService {

    private final VueloRepository vueloRepository;
    private final VueloMapper vueloMapper;

    @Autowired
    public VueloServiceImpl(VueloRepository vueloRepository, VueloMapper vueloMapper) {
        this.vueloRepository = vueloRepository;
        this.vueloMapper = vueloMapper;
    }

    @Override
    public VueloResponseDTO findById(Long id) {
        Vuelo vuelo = vueloRepository.findById(id).orElseThrow(() -> new VueloNotFoundException("No se encontro un vuelo con el id: " + id));
        return vueloMapper.vueloToVueloResponseDTO(vuelo);
    }

    @Override
    public List<VueloResponseDTO> findVuelosByDestino(String name) {
        List<Vuelo> vuelos = vueloRepository.findVuelosByDestino(name);
        return vuelos.stream()
                .map(vueloMapper::vueloToVueloResponseDTO)
                .collect(Collectors.toList());
    }

    @Override
    public VueloResponseDTO save(VueloRequestDTO vueloRequestDTO) {
        return vueloMapper.vueloToVueloResponseDTO(vueloRepository.save(vueloMapper.vueloRequestDTOToVuelo(vueloRequestDTO)));
    }

    @Override
    public List<VueloResponseDTO> findAll() {
        List<Vuelo> vuelos = vueloRepository.findAll();
        return vuelos.stream()
                .map(vueloMapper::vueloToVueloResponseDTO)
                .collect(Collectors.toList());
    }

    @Override
    public Long countVuelosByDestino(String destino) {
        return vueloRepository.countVuelosByDestino(destino);
    }

    @Override
    public List<VueloResponseDTO> findVuelosByOrigen(String origen) {
        List<Vuelo> vuelos = vueloRepository.findVuelosByOrigen(origen);
        return vuelos.stream()
                .map(vueloMapper::vueloToVueloResponseDTO)
                .collect(Collectors.toList());
    }

    @Override
    public List<VueloResponseDTO> vuelosOrderedByDestino() {
        List<Vuelo> vuelos = vueloRepository.vuelosOrderedByDestino();
        return vuelos.stream()
                .map(vueloMapper::vueloToVueloResponseDTO)
                .collect(Collectors.toList());
    }

    @Override
    public VueloResponseDTO findVueloByNumeroVuelo(UUID numVuelo) {
        Vuelo vuelo = vueloRepository.findVueloByNumeroVuelo(numVuelo).orElseThrow(() -> new VueloNotFoundException("No se encontro el vuelo con el numero: " + numVuelo));
        return vueloMapper.vueloToVueloResponseDTO(vuelo);
    }

    @Override
    public List<VueloResponseDTO> findVuelosByDestinoStartsWith(String letra) {
        List<Vuelo> vuelos = vueloRepository.findVuelosByDestinoStartsWith(letra);
        return vuelos.stream()
                .map(vueloMapper::vueloToVueloResponseDTO)
                .collect(Collectors.toList());
    }

    @Override
    public Long numberOfVueloByNameOfAerolinea(String nombre) {
        return vueloRepository.numberOfVueloByNameOfAerolinea(nombre);
    }
}
