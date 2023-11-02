package com.example.casemd4.controller;

import com.example.casemd4.model.Transactions;
import com.example.casemd4.service.Impl.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
}
