package com.example.casemd4.service;


import com.example.casemd4.model.Transactions;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ITransactionService<T> extends IGenerateService<Transactions>{

    Double getTotalMoneyByMonth(@Param("month") Integer month);

    Double getTotalMoneyByDay(@Param("day") Integer day);

    List<Transactions> find5TransactionFamilyExpenseNeares();

//    Iterable<Transactions> getAllByDateAndTransactionType();
}
