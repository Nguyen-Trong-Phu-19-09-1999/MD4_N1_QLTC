package com.example.casemd4.service.Impl;

import com.example.casemd4.model.TransactionType;
import com.example.casemd4.repository.ITransactionTypeRepository;
import com.example.casemd4.service.ITransactionTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class TransactionTypeService implements ITransactionTypeService {
    @Autowired
    ITransactionTypeRepository iTransactionTypeRepository;
    @Override
    public Iterable<TransactionType> findAll() {
        return iTransactionTypeRepository.findAll();
    }

    @Override
    public Optional<TransactionType> findById(Long id) {
        return iTransactionTypeRepository.findById(id);
    }

    @Override
    public void save(TransactionType transactionType) {
        iTransactionTypeRepository.save(transactionType);
    }

    @Override
    public void delete(Long id) {
        iTransactionTypeRepository.deleteById(id);
    }
}
