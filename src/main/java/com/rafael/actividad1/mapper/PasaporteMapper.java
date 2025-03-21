package com.rafael.actividad1.mapper;

import com.rafael.actividad1.dto.pasaporte.PasaporteDTO;
import com.rafael.actividad1.entity.Pasaporte;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface PasaporteMapper {

    @Mapping(source = "numero", target = "numero")
    @Mapping(source = "pasajero", target = "pasajero",ignore = true)
    PasaporteDTO pasaporteToPasaporteDTO(Pasaporte pasaporte);
}
