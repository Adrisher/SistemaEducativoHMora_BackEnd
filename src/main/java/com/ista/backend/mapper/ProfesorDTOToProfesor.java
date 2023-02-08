package com.ista.backend.mapper;

import com.ista.backend.persistence.entity.Profesor;
import com.ista.backend.service.dto.ProfesorDTO;
import org.springframework.stereotype.Component;

@Component
public class ProfesorDTOToProfesor implements IMapper<ProfesorDTO, Profesor> {
    @Override
    public Profesor map(ProfesorDTO in) {
        Profesor profesor= new Profesor();
        profesor.setCedula(in.getCedula());
        profesor.setNombre(in.getNombre());
        profesor.setSegundo_nombre(in.getSegundo_nombre());
        profesor.setPrimer_apellido(in.getPrimer_apellido());
        profesor.setSegundo_apellido(in.getSegundo_apellido());
        profesor.setGenero(in.getGenero());
        profesor.setFecha_nacimiento(in.getFecha_nacimiento());
        profesor.setCorreo(in.getCorreo());
        profesor.setEstado(true);
        profesor.setArea(in.getArea());
        return profesor;
    }
}
