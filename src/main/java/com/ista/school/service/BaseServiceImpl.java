package com.ista.school.service;

import com.ista.school.repository.BaseRepository;
import org.springframework.http.RequestEntity;

import java.io.Serializable;
import java.util.List;
import java.util.Optional;

public abstract class BaseServiceImpl<T, ID extends Serializable> implements BaseService<T, ID> {

    private final BaseRepository<T, ID> baseRepository;

    public BaseServiceImpl(BaseRepository<T, ID> baseRepository) {
        this.baseRepository = baseRepository;
    }

    @Override
    public List<T> findAll() {
        return baseRepository.findAll();
    }

    @Override
    public Optional<T> findById(ID id) {
        return baseRepository.findById(id);
    }

    @Override
    public T save(T entity) {
        return baseRepository.save(entity);
    }

    @Override
    public RequestEntity<?> deleteById(ID id) {
        baseRepository.deleteById(id);
        return null;
    }

    @Override
    public T update(T t, ID id) {
        Optional<T> current = baseRepository.findById(id);
        T update = current.get();
        update = baseRepository.save(t);
        return update;
    }

    @Override
    public List<T> saveAll(List<T> detalles) {
        return baseRepository.saveAll(detalles);
    }

}
