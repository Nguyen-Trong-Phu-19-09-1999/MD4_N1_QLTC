package com.example.casemd4.controller;

import com.example.casemd4.model.TransactionType;
import com.example.casemd4.service.Impl.TransactionTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@CrossOrigin("*")
@RequestMapping("/api/transactionType")
public class TransactionTypeController {

    @Autowired
    TransactionTypeService transactionTypeService;

    @GetMapping
    public ResponseEntity<Iterable<TransactionType>> findAllTransactionType(){
        return new ResponseEntity<>(transactionTypeService.findAll(),HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<TransactionType> findTransactionTypeById(@PathVariable Long id){
        return new ResponseEntity<>(transactionTypeService.findById(id).get(),HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<TransactionType> saveTransactionType(@RequestBody TransactionType transactionType){
        transactionTypeService.save(transactionType);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<TransactionType> updateTransactionType(@PathVariable Long id, @RequestBody TransactionType transactionType){
        Optional<TransactionType> transactionTypeOptional = transactionTypeService.findById(id);
        if(transactionTypeOptional.isPresent()){
            transactionType.setId(transactionTypeOptional.get().getId());
            transactionTypeService.save(transactionType);
            return new ResponseEntity<>( HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<TransactionType> deleteTransactionType(@PathVariable Long id){
        Optional<TransactionType> transactionTypeOptional = transactionTypeService.findById(id);
        if(transactionTypeOptional.isPresent()){
            transactionTypeService.delete(transactionTypeOptional.get().getId());
            return new ResponseEntity<>(transactionTypeOptional.get(), HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }}

