package com.pureamorous.spring_rbac_jwt.controller;


import com.pureamorous.spring_rbac_jwt.entities.Book;
import com.pureamorous.spring_rbac_jwt.service.BookService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "api/books",produces = MediaType.APPLICATION_JSON_VALUE)
public class BookController {
    @Autowired
    private BookService service;

    @PreAuthorize("hasAuthority('select')")
    @GetMapping
    public List<Book> getBooks(){
        return service.findAll();
    }

    @PreAuthorize("hasAuthority('select')")
    @GetMapping("/{id}")
    public Book find(@PathVariable Integer id){
        return service.findById(id);
    }

    @PreAuthorize("hasAuthority('insert')")
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public void create(@Valid @RequestBody Book book){
        service.save(book);
    }

    @PreAuthorize("hasAuthority('remove')")
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void remove(@PathVariable Integer id) {
        service.remove(id);
    }

    @PreAuthorize("hasAuthority('change')")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PutMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public void update(@Valid @RequestBody Book book) {
        service.update(book);
    }
}
