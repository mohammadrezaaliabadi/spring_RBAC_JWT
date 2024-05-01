package com.pureamorous.spring_rbac_jwt.controller;


import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class BookController {
    @RequestMapping("get")

    public String getService(){
        return "My service";
    }
}
