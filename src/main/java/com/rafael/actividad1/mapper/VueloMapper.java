package com.rafael.actividad1.mapper;

import com.rafael.actividad1.dto.request.VueloRequestDTO;
import com.rafael.actividad1.dto.response.VueloResponseDTO;
import com.rafael.actividad1.entity.Vuelo;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", uses = {AerolineaMapper.class, ReservaMapper.class})
public interface VueloMapper {

    VueloResponseDTO vueloToVueloResponseDTO(Vuelo vuelo);

    Vuelo vueloRequestDTOToVuelo(VueloRequestDTO vueloRequestDTO);
}
