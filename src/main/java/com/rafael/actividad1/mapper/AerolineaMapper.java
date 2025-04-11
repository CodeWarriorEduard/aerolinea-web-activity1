package com.rafael.actividad1.mapper;

import com.rafael.actividad1.dto.request.AerolineaRequestDTO;
import com.rafael.actividad1.dto.response.AerolineaResponseDTO;
import com.rafael.actividad1.dto.response.AerolineaVuelosResponseDTO;
import com.rafael.actividad1.entity.Aerolinea;
import com.rafael.actividad1.entity.Vuelo;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;
import java.util.Optional;

@Mapper(componentModel = "spring", uses = Vuelo.class)
public interface AerolineaMapper {
    @Mapping(target = "nombre", source = "nombre")
    @Mapping(target = "vuelos", source = "vuelos")
    AerolineaRequestDTO aerolineaRequestDto(Aerolinea aerolinea);

    Aerolinea aerolineaRequestDtoToAerolinea(AerolineaRequestDTO aerolinea);

    @Mapping(target = "nombre", source = "nombre")
    AerolineaResponseDTO aerolineaResponseDto(Aerolinea aerolinea);

    @Mapping(target = "name", source = "nombre")
    List<AerolineaResponseDTO> toListOfAerolineaResponseDTO(List<Aerolinea> aerolineas);


    List<AerolineaVuelosResponseDTO> aerolineaVuelosResponseDtos(List<Aerolinea> aerolinea);

}
