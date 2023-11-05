package com.example.casemd4.service.Impl;

import com.example.casemd4.model.Role;
import com.example.casemd4.repository.IRoleRepository;
import com.example.casemd4.service.IRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class RoleService implements IRoleService {
    @Autowired
    private IRoleRepository iRoleRepository;

    @Override
    public Role findByName(String roleName) {
        return iRoleRepository.findByName(roleName);
    }

    @Override
    public Iterable<Role> findAll() {
        return iRoleRepository.findAll();
    }

    @Override
    public Optional<Role> findById(Long id) {
        return iRoleRepository.findById(id);
    }

    @Override
    public void save(Role role) {
        iRoleRepository.save(role);
    }

    @Override
    public void delete(Long id) {
        iRoleRepository.deleteById(id);
    }
}
