package com.pureamorous.spring_rbac_jwt.repositories;

import com.pureamorous.spring_rbac_jwt.entities.Role;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RoleRepository extends JpaRepository<Role, Integer> {

    Optional<Role> findByName(String name);

}
