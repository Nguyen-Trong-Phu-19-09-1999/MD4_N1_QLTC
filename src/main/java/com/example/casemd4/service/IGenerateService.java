package com.example.casemd4.service;

import com.example.casemd4.model.TransactionType;

import java.util.Optional;

public interface IGenerateService<T> {
    public Iterable<T> findAll();

    public Optional<T> findById(Long id);

    public void save(T t);

    public void delete(Long id);
}
