package com.pureamorous.spring_rbac_jwt;

import com.pureamorous.spring_rbac_jwt.entities.Book;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookRepository extends JpaRepository<Book,Integer> {
    Boolean existsByName(String name);
}
