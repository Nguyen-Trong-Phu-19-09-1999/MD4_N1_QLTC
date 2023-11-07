package com.example.casemd4.repository;


import com.example.casemd4.model.User;
import com.example.casemd4.security.jwt.CustomAccessDeniedHandler;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IUserRepository extends JpaRepository<User, Long> {
    List<User> findOneByUsername(String name);
    List<User> findAllByUsernameAndPassword(String name, String password);
    User findByUsername(String username);



}
