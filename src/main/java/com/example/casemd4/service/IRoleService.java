package com.example.casemd4.service;

import com.example.casemd4.model.Role;

public interface IRoleService extends IGenerateService<Role> {
    Role findByName(String roleName);
}
