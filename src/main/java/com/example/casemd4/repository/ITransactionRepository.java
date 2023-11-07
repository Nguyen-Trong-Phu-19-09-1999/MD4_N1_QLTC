package com.example.casemd4.repository;


import com.example.casemd4.model.Transactions;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;


@Repository
public interface ITransactionRepository extends JpaRepository<Transactions, Long> {

    // tổng chi tiêu gia đình trong 1 tháng
    @Query("SELECT SUM(t.money) FROM Transactions t " +
            "WHERE MONTH(t.date) = :month ")
    Double getTotalMoneyByMonth(@Param("month") Integer month);

    // tổng chi tiêu gia đình trong 1 tháng
    @Query("SELECT SUM(t.money) FROM Transactions t " +
            "WHERE day(t.date) = :day ")
    Double getTotalMoneyByDay(@Param("day") Integer day);

    // lấy ra 5 giao dịch chi tiêu gia đình gần nhất
    @Query("SELECT t FROM Transactions t " +
            "ORDER BY t.date DESC")
    List<Transactions> findTopByOrOrderByDateDateDesc();

//    @Query("SELECT t FROM Transactions t JOIN transaction_type c ON t.transaction_type_id = :c WHERE DAY(t.date) = :date")
//    Iterable<Transactions> getAllByDateAndTransactionType();
//


}
