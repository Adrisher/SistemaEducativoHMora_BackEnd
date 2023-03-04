package com.ista.school.service;

import com.ista.school.model.entity.Detalle;
import com.ista.school.model.entity.Materia;
import com.ista.school.repository.BaseRepository;
import com.ista.school.repository.DetalleRepository;
import com.ista.school.repository.MateriaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;


@Service
public class DetalleServiceImpl extends BaseServiceImpl<Detalle, Long> implements DetalleService {

    @Autowired
    private DetalleRepository repository;
    @Autowired
    private MateriaRepository mRepository;

    public DetalleServiceImpl(BaseRepository<Detalle, Long> baseRepository) {
        super(baseRepository);
    }

    @Override
    public Detalle buscarPorMateria(Long id) {
        Optional<Materia> m=mRepository.findById(id);
        Materia ma=m.get();
        return repository.findByMateria(ma);
    }
}
