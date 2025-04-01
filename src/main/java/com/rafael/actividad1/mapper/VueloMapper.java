package com.rafael.actividad1.mapper;

import com.rafael.actividad1.dto.request.VueloRequestDTO;
import com.rafael.actividad1.dto.response.VueloResponseDTO;
import com.rafael.actividad1.entity.Vuelo;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", uses = {AerolineaMapper.class, ReservaMapper.class})
public interface VueloMapper {

    VueloResponseDTO vueloToVueloResponseDTO(Vuelo vuelo);

    @Mapping(target = "reservas", source = "reservas", ignore = true)
    Vuelo vueloRequestDTOToVuelo(VueloRequestDTO vueloRequestDTO);
}
