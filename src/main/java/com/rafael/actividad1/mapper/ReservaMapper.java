package com.rafael.actividad1.mapper;
import com.rafael.actividad1.dto.request.ReservaRequestDTO;
import com.rafael.actividad1.dto.response.ReservaResponseDTO;
import com.rafael.actividad1.entity.Reserva;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", uses = {PasajeroMapper.class, VueloMapper.class})
public interface ReservaMapper {

    ReservaResponseDTO reservaToReservaResponseDTO(Reserva reserva);

    @Mapping(target = "vuelo", source = "vuelo", ignore = true)
    Reserva reservaRequestDTOToReserva(ReservaRequestDTO reservaRequestDTO);
}
