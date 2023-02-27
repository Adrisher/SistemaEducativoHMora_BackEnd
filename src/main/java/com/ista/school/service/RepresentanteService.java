package com.ista.school.service;

import com.ista.school.model.entity.Representante;

public interface RepresentanteService extends BaseService<Representante, Long> {

    Representante findByCedula(String cedula);

}
