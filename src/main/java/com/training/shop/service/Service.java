package com.training.shop.service;

public interface Service<T> {

    T create(T entity);

    Iterable<T> findAll();

    T findById(Long id);

    void deleteById(Long id);

    T update(T entity);
}
