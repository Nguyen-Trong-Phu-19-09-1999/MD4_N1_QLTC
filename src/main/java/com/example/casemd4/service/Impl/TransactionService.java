package com.example.casemd4.service.Impl;

import com.example.casemd4.model.Transactions;
import com.example.casemd4.repository.ITransactionRepository;
import com.example.casemd4.service.ITransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class TransactionService implements ITransactionService<Transactions> {
    @Autowired
    private ITransactionRepository iTransactionRepository;

    @Override
    public Iterable<Transactions> findAll() {
        return iTransactionRepository.findAll();
    }

    @Override
    public Optional<Transactions> findById(Long id) {
        return iTransactionRepository.findById(id);
    }

    @Override
    public void save(Transactions transactions) {
        iTransactionRepository.save(transactions);
    }

    @Override
    public void delete(Long id) {
        iTransactionRepository.deleteById(id);
    }
}
