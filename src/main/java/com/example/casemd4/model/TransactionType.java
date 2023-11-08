package com.example.casemd4.model;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "TransactionType")
public class TransactionType {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

}
