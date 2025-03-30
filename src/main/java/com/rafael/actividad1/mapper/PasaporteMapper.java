package com.rafael.actividad1.mapper;

import com.rafael.actividad1.dto.response.PasaporteResponseDTO;
import com.rafael.actividad1.entity.Pasaporte;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface PasaporteMapper {

    @Mapping(target = "numero", source = "numero")
    @Mapping(target = "pasajero", source = "pasajero",ignore = true)
    PasaporteResponseDTO pasaporteToPasaporteResponseDTO(Pasaporte pasaporte);
}
