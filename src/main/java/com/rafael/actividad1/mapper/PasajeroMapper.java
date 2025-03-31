package com.rafael.actividad1.mapper;

import com.rafael.actividad1.dto.response.PasajeroResponseDTO;
import com.rafael.actividad1.entity.Pasajero;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring",uses = {PasaporteMapper.class, ReservaMapper.class})
public interface PasajeroMapper {
    @Mapping(target = "nombre", source = "nombre")
    @Mapping(target = "nid",source = "nid")
    PasajeroResponseDTO pasajeroToPasajeroResponseDTO(Pasajero pasajero);
}
