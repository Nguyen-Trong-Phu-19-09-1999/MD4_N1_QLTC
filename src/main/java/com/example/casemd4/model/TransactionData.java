package com.example.casemd4.model;

import lombok.Data;

import java.util.Date;

@Data
public class TransactionData {
    private Date date;
    private Double money;
}
