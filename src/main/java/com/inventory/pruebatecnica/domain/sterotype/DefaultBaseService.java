package com.inventory.pruebatecnica.domain.sterotype;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

public abstract class DefaultBaseService<T extends BaseEntity> implements BaseService<T> {

    private static final String EMPTY = "";
    private static final String WILDCARD = "Service";
    public static final String NOT_FOUND_MESSAGE = "No se pudo encontrar el registro con ID: %s. %s.";

    @Override
    public T save(T t) {
        return this.getRepository().save(t);
    }

    @Transactional
    public T create(T entity) {
        return this.getRepository().save(entity);
    }

    @Override
    public Page<T> findAll(Pageable pageable) {
        return this.getRepository().findAll(pageable);
    }

    @Override
    public List<T> findAll() {
        return this.getRepository().findAll();
    }

    @Override
    public Optional<T> getById(Long id) {
        return this.getRepository().findById(id);
    }

    @Override
    public T findById(Long id) {
        return this.getRepository().findById(id).orElseThrow(() -> {
            final String ENTITY_NAME = getClass().getSimpleName().replace(WILDCARD, EMPTY);
            final String message = String.format(NOT_FOUND_MESSAGE, id, ENTITY_NAME);
            return new EntityNotFoundException(message);
        });
    }


    protected abstract BaseRepository<T> getRepository();


}
