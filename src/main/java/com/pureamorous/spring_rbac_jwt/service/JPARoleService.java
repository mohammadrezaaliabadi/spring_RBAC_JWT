package com.pureamorous.spring_rbac_jwt.service;

import com.pureamorous.spring_rbac_jwt.entities.Role;
import com.pureamorous.spring_rbac_jwt.repositories.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class JPARoleService implements RoleService {
    @Autowired
    private RoleRepository repository;

    public Role insert(Role role){
        return repository.save(role);
    }
}
