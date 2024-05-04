package com.pureamorous.spring_rbac_jwt.controller;


import com.pureamorous.spring_rbac_jwt.entities.Book;
import com.pureamorous.spring_rbac_jwt.service.BookService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "api/books",produces = MediaType.APPLICATION_JSON_VALUE)
public class BookController {
    @Autowired
    private BookService bookService;


    @GetMapping
    public List<Book> getBooks(){
        return bookService.findAll();
    }

    @GetMapping("/{id}")
    public Book find(@PathVariable Integer id){
        return bookService.findById(id);
    }
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public void create(@Valid @RequestBody Book book){
        bookService.save(book);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void remove(@PathVariable Integer id) {
        bookService.remove(id);
    }


    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PutMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public void update(@Valid @RequestBody Book book) {
        bookService.update(book);
    }
}
