package com.example.casemd4.service;

import com.example.casemd4.model.TransactionType;

import java.util.Optional;

public interface IGenerateService<T> {
    Iterable<T> findAll();

    Optional<T> findById(Long id);

    public void save(T t);

    void delete(Long id);
}
