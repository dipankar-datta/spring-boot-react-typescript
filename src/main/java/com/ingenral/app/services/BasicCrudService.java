package com.ingenral.app.services;

import java.util.List;
import java.util.Optional;
import java.util.Set;

public interface BasicCrudService<T> {

    List<T> list();

    T create(T item);

    T update(T item);

    <S extends T> Iterable<S> saveAll(Iterable<S> items);

    Optional<T> getById(Long id);

    void deleteById(Long id);

    void delete(T item);

}
