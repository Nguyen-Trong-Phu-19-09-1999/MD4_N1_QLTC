package com.example.casemd4.controller;

import com.example.casemd4.model.TransactionData;
import com.example.casemd4.model.Transactions;
import com.example.casemd4.service.Impl.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin("*")
@RequestMapping("/transaction")
public class TransactionController {
    @Autowired
    TransactionService transactionService;

    @GetMapping
    public ResponseEntity<Iterable<Transactions>> showAllTran(){
        return new ResponseEntity<>(transactionService.findAll(),HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<?> addTran(@RequestBody Transactions transactions){
        transactionService.save(transactions);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> findById(@PathVariable Long id){
        Optional<Transactions> find = transactionService.findById(id);
        return new ResponseEntity<>(find,HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Iterable<Transactions>> update(@RequestBody Transactions transactions,@PathVariable Long id){
        Optional<Transactions> transactions1 = transactionService.findById(id);
        if (transactions1.isPresent()){
            transactions.setId(transactions1.get().getId());
            transactionService.save(transactions);
            return new ResponseEntity<>(HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id){
        Optional<Transactions> transactions1 = transactionService.findById(id);
        if (transactions1.isPresent()){
            transactionService.delete(id);
            return new ResponseEntity<>(HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
    @GetMapping("/chart-data")
    public List<Transactions> getData(){
        Iterable<Transactions> data = transactionService.findAll();
        return (List<Transactions>) data;
    }
    @GetMapping("/chart")
    public List<TransactionData> getTransactionData() {
        Iterable<Transactions> transactions = transactionService.findAll();
        List<TransactionData> transactionDataList = new ArrayList<>();

        for (Transactions transaction : transactions) {
            TransactionData transactionData = new TransactionData();
            transactionData.setDate(transaction.getDate());
            transactionData.setMoney(transaction.getMoney());
            transactionDataList.add(transactionData);
        }

        return transactionDataList;
    }
    @GetMapping("/totalMoney/{month}")
    public Double getTotalMoneyByMonth(@PathVariable("month") Integer month) {
        return transactionService.getTotalMoneyByMonth(month);
    }

    @GetMapping("/MoneyDay/{day}")
    public Double getTotalMoneyByDay(@PathVariable("day") Integer day) {
        return transactionService.getTotalMoneyByDay(day);
    }

    @GetMapping("/family-expense")
    public List<Transactions> findTopByOrOrderByDateDateDesc() {
        return transactionService.find5TransactionFamilyExpenseNeares();
    }

    @GetMapping("/abc/{day}")
    public ResponseEntity<Iterable<Transactions>> getAllByDate(@PathVariable Integer day){
        return new ResponseEntity<>(transactionService.getAllByDate(day),HttpStatus.OK);
    }
    @GetMapping("/xyz/{month}")
    public ResponseEntity<Iterable<Transactions>> getAllByMonth(@PathVariable Integer month){
        return new ResponseEntity<>(transactionService.getMonth(month),HttpStatus.OK);
    }

    @GetMapping("/hug/{year}")
    public ResponseEntity<Iterable<Transactions>> getYear(@PathVariable Integer year){
        return new ResponseEntity<>(transactionService.getYear(year),HttpStatus.OK);
    }
}
