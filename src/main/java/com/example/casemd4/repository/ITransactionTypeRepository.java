package com.example.casemd4.repository;



import com.example.casemd4.model.TransactionType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ITransactionTypeRepository extends JpaRepository<TransactionType, Long> {
}
