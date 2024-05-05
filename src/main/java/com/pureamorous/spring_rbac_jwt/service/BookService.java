package com.pureamorous.spring_rbac_jwt.service;

import com.pureamorous.spring_rbac_jwt.entities.Book;

import java.util.List;

public interface BookService {

    Book findById(Integer id);

    Boolean isExists(String name) ;

    void remove(Integer id);

    Book save(Book book);

    void update(Book book);

    List<Book> findAll();

    String getService();
}
