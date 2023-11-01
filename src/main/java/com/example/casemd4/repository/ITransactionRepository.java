package com.example.casemd4.repository;


import com.example.casemd4.model.Transactions;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface ITransactionRepository extends JpaRepository<Transactions, Long> {
}
