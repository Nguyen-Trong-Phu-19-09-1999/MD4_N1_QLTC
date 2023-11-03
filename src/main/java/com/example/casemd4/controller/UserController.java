package com.example.casemd4.controller;

import com.example.casemd4.model.User;
import com.example.casemd4.repository.IUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin("*")
@RequestMapping("/users")
public class UserController {
    @Autowired
    IUserRepository iUserRepository;
    @GetMapping
    List<User> lisUsers(){
        return iUserRepository.findAll();
    }
    @PostMapping("/register")
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
    @PostMapping("/login")
    public ResponseEntity login(@RequestBody User user){
        List<User> users = iUserRepository.findAllByNameAndPassword(user.getName(), user.getPassword());
        if(users.size()==1){
            return new ResponseEntity(users,HttpStatus.OK);
        }
        else {
            return new ResponseEntity("User name or password not correct",HttpStatus.NOT_IMPLEMENTED);
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
