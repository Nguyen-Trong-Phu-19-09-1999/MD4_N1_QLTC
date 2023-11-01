package com.example.casemd4.controller;

import com.example.casemd4.model.TransactionType;
import com.example.casemd4.service.ITransactionTypeService;
import com.example.casemd4.service.Impl.TransactionTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.CustomAutowireConfigurer;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.ServletRequestBindingException;
import org.springframework.web.bind.annotation.*;

import java.awt.font.OpenType;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/tracsactiontype")
public class TransactionTypeController {
    @Autowired
    TransactionTypeService transactionTypeService;

    @GetMapping
    public ResponseEntity<Iterable<TransactionType>> findAllTransactionType(){
        List<TransactionType> transactionTypes = (List<TransactionType>) transactionTypeService.findAll();
        if(transactionTypes.isEmpty()){
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(transactionTypes, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<TransactionType> findTransactionTypeById(@PathVariable Long id){
        Optional<TransactionType> transactionTypeOptional  = transactionTypeService.findById(id);
        if(!transactionTypeOptional.isPresent()){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(transactionTypeOptional.get(), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<TransactionType> saveTransactionType(@RequestBody TransactionType transactionType){
        return new ResponseEntity<TransactionType>(transactionTypeService.save(transactionType),HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<TransactionType> updateTransactionType(@PathVariable Long id, @RequestBody TransactionType transactionType){
        Optional<TransactionType> transactionTypeOptional = transactionTypeService.findById(id);
        if(transactionTypeOptional.isPresent()){
            transactionType.setId(transactionTypeOptional.get().getId());
            return new ResponseEntity<>(transactionTypeService.save(transactionType), HttpStatus.OK);
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
    }
}
