package com.example.casemd4.controller;

import com.example.casemd4.model.Role;
import com.example.casemd4.repository.IRoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/role")
public class RoleController {
    @Autowired
    IRoleRepository iRoleRepository;
    @PostMapping("/insert")
    public ResponseEntity insertRole(@RequestBody Role role){
        iRoleRepository.save(role);
        return new ResponseEntity<>("Them thanh cong", HttpStatus.OK);

    }
    @GetMapping()
    List<Role> listRole(){
        System.out.println("a");
        return iRoleRepository.findAll();
    }


}
