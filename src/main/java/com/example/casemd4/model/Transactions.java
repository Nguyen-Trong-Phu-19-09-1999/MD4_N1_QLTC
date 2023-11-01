package com.example.casemd4.model;

import lombok.Data;

import javax.persistence.*;
import java.sql.Date;

@Data
@Entity
public class Transactions {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private User user;

    @ManyToOne
    private TransactionType transactionType;

    private Double money;

    private Date date;

    private String note;
}

// transaction: id, User, money, type, time, note
// type: id, name, category
// category: id, name (Thu, Chi)
