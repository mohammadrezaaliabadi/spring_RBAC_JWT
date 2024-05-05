package com.pureamorous.spring_rbac_jwt.repositories;

import com.pureamorous.spring_rbac_jwt.entities.Authority;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AuthorityRepository extends JpaRepository<Authority,Integer> {
    Optional<Authority> findByAuthority(String authority);
}
