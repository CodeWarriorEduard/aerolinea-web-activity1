package com.rafael.actividad1.mapper;

import com.rafael.actividad1.dto.pasajero.PasajeroDTO;
import com.rafael.actividad1.dto.pasaporte.PasaporteDTO;
import com.rafael.actividad1.entity.Pasajero;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring",uses = {PasaporteDTO.class})
public interface PasajeroMapper {

    @Mapping(target = "nombre", source = "nombre")
    @Mapping(target = "nid",source = "nid")
    @Mapping(target = "pasaporte", source = "pasaporte")
    PasajeroDTO pasajeroToPasajeroDTO(Pasajero pasajero);
}
