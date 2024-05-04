package com.pureamorous.spring_rbac_jwt.controller;

import com.pureamorous.spring_rbac_jwt.service.DataNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class MyExceptionHandler {
    @ExceptionHandler(DataNotFoundException.class)
    public ResponseEntity<Map<String,String>> handlerDataNotFoundException(DataNotFoundException e){
        return new ResponseEntity<>(
            generateMap(e), HttpStatus.NOT_FOUND
        );
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String,String>> handlerMethodArgumentNotValidException(MethodArgumentNotValidException e){
        return new ResponseEntity<>(
                generateMap(e), HttpStatus.BAD_REQUEST
        );
    }



    private Map<String,String> generateMap(Throwable throwable){
        var response = new HashMap<String, String>();
        response.put("timestamp", String.valueOf(System.currentTimeMillis()));
        response.put("message", throwable.getMessage());
        return response;
    }
}
