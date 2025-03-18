package com.rafael.actividad1.service.impl;

import com.rafael.actividad1.entity.Aerolinea;
import com.rafael.actividad1.repository.AerolineaRepository;
import com.rafael.actividad1.service.AerolineaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AerolineaServiceImpl implements AerolineaService {

    @Autowired
    private final AerolineaRepository aerolineaRepository;

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

    @Override
    public Optional<Aerolinea> findAerolineaById(Long id) {

        return ;
    }

    @Override
    public Aerolinea saveAerolinea(Aerolinea aerolinea) {
        return null;
    }

    @Override
    public List<Aerolinea> findAllAerolineasInDb() {
        return List.of();
    }

    @Override
    public Optional<Aerolinea> findAerolineaByNombre(String nombre) {
        return Optional.empty();
    }
}
