package com.example.casemd4.controller;

import com.example.casemd4.model.User;
import com.example.casemd4.repository.IUserRepository;
import com.example.casemd4.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("users")
public class UserController {
    @Autowired
    IUserRepository iUserRepository;
    @GetMapping
    List<User> lisUsers(){
        return iUserRepository.findAll();
    }
    @PostMapping("")
    public ResponseEntity addUser(@RequestBody User user){
        List<User> foundUser = iUserRepository.findByName(user.getName().trim());
        if(foundUser.size() > 0){
            return new ResponseEntity("User already exists", HttpStatus.NOT_IMPLEMENTED);
        }
        else {
            iUserRepository.save(user);
            return new ResponseEntity("Add user successfully", HttpStatus.OK);
        }

    }
    @PutMapping("/{id}")
    public ResponseEntity<String> update(@RequestBody User user, @PathVariable Long id){
        boolean exists = iUserRepository.existsById(id);
        if (exists){
            user.setId(id);
            iUserRepository.save(user);
            return new ResponseEntity("Update user successfully", HttpStatus.OK);
        }else {
            return new ResponseEntity("cannot find this id",HttpStatus.NOT_FOUND);
        }
    }
    @DeleteMapping("/{id}")
    public ResponseEntity deleteUser(@PathVariable Long id){
        boolean exists = iUserRepository.existsById(id);
        if (exists){
            iUserRepository.deleteById(id);
            return new ResponseEntity("Delete user successfully", HttpStatus.OK);
        }
        else {
            return new ResponseEntity("Cannot find this id", HttpStatus.NOT_FOUND);
        }

    }
    @GetMapping("/{id}")
    public ResponseEntity findById(@PathVariable Long id){
        Optional<User> foundProduct = iUserRepository.findById(id);
        return new ResponseEntity(foundProduct,HttpStatus.OK);
    }






}
