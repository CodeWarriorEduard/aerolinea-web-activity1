package com.rafael.actividad1.service.impl;

import com.rafael.actividad1.dto.request.AerolineaRequestDTO;
import com.rafael.actividad1.dto.response.AerolineaResponseDTO;
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

    @Autowired
    private final AerolineaRepository aerolineaRepository;

    @Autowired
    AerolineaMapper aerolineaMapper;

    public AerolineaServiceImpl(AerolineaRepository aerolineaRepository) {
        this.aerolineaRepository = aerolineaRepository;
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

    // Retornamos en vez de un optional un dto
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
        return aerolineaMapper.aerolineaResponseDtoList(aerolineas);
    }

    @Override
    public Optional<Aerolinea> findAerolineaByNombre(String nombre) {
        return aerolineaRepository.findByNombre(nombre);
    }

    @Override
    public List<Aerolinea> aerolineaStartsWithA() {
        return aerolineaRepository.aerolineaStartsWithA();
    }

    @Override
    public List<Aerolinea> findAerolineaByPassengerName(String nombre) {
        return aerolineaRepository.findAerolineaByPassengerName(nombre);
    }

    @Override
    public List<Aerolinea> aerolineasWithTwoFlightsx() {
        return aerolineaRepository.aerolineasWithTwoFlightsx();
    }

    @Override
    public List<Aerolinea> findAllOrderedByName() {
        return aerolineaRepository.findAllOrderedByName();
    }

    @Override
    public Long countAllAerolineas() {
        return aerolineaRepository.countAllAerolineas();
    }
}
