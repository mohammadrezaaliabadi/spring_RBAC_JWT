package com.pureamorous.spring_rbac_jwt.service;

import com.pureamorous.spring_rbac_jwt.entities.Authority;
import com.pureamorous.spring_rbac_jwt.repositories.AuthorityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class JPAAuthorityService implements AuthorityService {
    @Autowired
    private AuthorityRepository repository;

    public Authority insert(Authority authority){
        return repository.save(authority);
    }
}
