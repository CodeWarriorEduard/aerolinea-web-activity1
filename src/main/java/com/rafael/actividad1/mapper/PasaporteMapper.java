package com.rafael.actividad1.mapper;

import com.rafael.actividad1.dto.pasajero.PasaporteDTO;
import com.rafael.actividad1.dto.pasaporte.PasajeroDTO;
import com.rafael.actividad1.entity.Pasaporte;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface PasaporteMapper {

    @Mapping(target = "numero", source = "numero")
    @Mapping(target = "pasajero", source = "pasajero",ignore = true)
    PasaporteDTO pasaporteToPasaporteDTO(Pasaporte pasaporte);
}
