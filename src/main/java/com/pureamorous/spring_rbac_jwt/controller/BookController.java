package com.pureamorous.spring_rbac_jwt.controller;


import com.pureamorous.spring_rbac_jwt.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class BookController {
    @Autowired
    private BookService bookService;

    @RequestMapping("get")

    public String getService(){
        return bookService.getService();
    }
}
