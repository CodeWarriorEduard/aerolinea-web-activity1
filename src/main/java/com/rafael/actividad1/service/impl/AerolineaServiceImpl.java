package com.rafael.actividad1.service.impl;

import com.rafael.actividad1.dto.request.AerolineaRequestDTO;
import com.rafael.actividad1.dto.response.AerolineaResponseDTO;
import com.rafael.actividad1.dto.response.AerolineaVuelosResponseDTO;
import com.rafael.actividad1.entity.Aerolinea;
import com.rafael.actividad1.exceptions.AerolineaNotFoundException;
import com.rafael.actividad1.mapper.AerolineaMapper;
import com.rafael.actividad1.repository.AerolineaRepository;
import com.rafael.actividad1.service.AerolineaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import javax.swing.text.html.Option;
import java.util.List;
import java.util.Optional;

@Service
public class AerolineaServiceImpl implements AerolineaService {


    private final AerolineaRepository aerolineaRepository;
    AerolineaMapper aerolineaMapper;

    @Autowired
    public AerolineaServiceImpl(AerolineaRepository aerolineaRepository, AerolineaMapper aerolineaMapper) {
        this.aerolineaRepository = aerolineaRepository;
        this.aerolineaMapper = aerolineaMapper;
    }

    @Override
    public boolean deleteAerolineaById(Long id) {
            // Buscamos si existe
            boolean success = false;

            Optional<Aerolinea> aerolinea = aerolineaRepository.findById(id);

            if(aerolinea.isPresent()){
                aerolineaRepository.deleteById(id);
                success = true;
            }

            return success;
    }

    // Retornamos en vez de un optional un dto, para retornar excepción de ejemplo
    @Override
    public AerolineaResponseDTO findAerolineaById(Long id) {
        Aerolinea aerolinea = aerolineaRepository.findById(id).orElseThrow(()-> new AerolineaNotFoundException("Aerolinea no encontrada"));
        return aerolineaMapper.aerolineaResponseDto(aerolinea);
    }

    @Override
    public AerolineaResponseDTO saveAerolinea(Aerolinea aerolinea) {
        return aerolineaMapper.aerolineaResponseDto(aerolineaRepository.save(aerolinea));
    }

    @Override
    public List<AerolineaResponseDTO> findAllAerolineasInDb() {
        List<Aerolinea> aerolineas = aerolineaRepository.findAll();
        return aerolineaMapper.toListOfAerolineaResponseDTO(aerolineas);
    }

    @Override
    public Optional<AerolineaResponseDTO> findAerolineaByNombre(String nombre) {
        return aerolineaRepository.findByNombre(nombre)
                .map(aerolineaMapper::aerolineaResponseDto);
    }

    @Override
    public List<AerolineaResponseDTO> aerolineaStartsWithA() {
        return aerolineaMapper.toListOfAerolineaResponseDTO(aerolineaRepository.aerolineaStartsWithA());
    }

    @Override
    public List<AerolineaResponseDTO> findAerolineaByPassengerName(String nombre) {
        return aerolineaMapper.toListOfAerolineaResponseDTO(aerolineaRepository.findAerolineaByPassengerName(nombre));
    }

    @Override
    public List<AerolineaVuelosResponseDTO> aerolineasWithTwoFlightsx() {
        return aerolineaMapper.aerolineaVuelosResponseDtos(aerolineaRepository.aerolineasWithTwoFlightsx());
    }

    @Override
    public List<AerolineaResponseDTO> findAllOrderedByName() {
        return aerolineaMapper.toListOfAerolineaResponseDTO(aerolineaRepository.findAllOrderedByName());
    }

    // Estructurar mejor respuesta
    @Override
    public Long countAllAerolineas() {
        return aerolineaRepository.countAllAerolineas();
    }
}
