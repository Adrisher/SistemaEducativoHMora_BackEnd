package com.ista.school.service;

import com.ista.school.model.entity.Detalle;

public interface DetalleService extends BaseService<Detalle, Long> {

    Detalle buscarPorMateria(Long id);
}
