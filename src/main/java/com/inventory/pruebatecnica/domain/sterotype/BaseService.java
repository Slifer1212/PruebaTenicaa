package com.inventory.pruebatecnica.domain.sterotype;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.lang.NonNull;

import java.util.List;
import java.util.Optional;

public interface BaseService<T extends BaseEntity> {

    T save(T t);

    T create(T entity);

    T findById(Long id);

    Optional<T> getById(Long id);

    Page<T> findAll(Pageable pageable);

    List<T> findAll();


    static <T extends BaseEntity> BaseService<T> of(@NonNull final BaseRepository<T> repository) {
        return new DefaultBaseService<>() {
            @Override
            protected BaseRepository<T> getRepository() {
                return repository;
            }
        };
    }
}
