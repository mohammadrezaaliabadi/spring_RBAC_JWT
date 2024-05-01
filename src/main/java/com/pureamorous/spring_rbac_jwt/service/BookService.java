package com.pureamorous.spring_rbac_jwt.service;

import com.pureamorous.spring_rbac_jwt.entities.Book;
import com.pureamorous.spring_rbac_jwt.repositories.BookRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookService {
    @Autowired
    private BookRepository bookRepository;

    @Transactional
    public Book save(Book book) {
        return bookRepository.save(book);
    }

    public Book findById(Integer id) {
        var result = bookRepository.findById(id);

        return result.get();
    }

    public Boolean isExists(String name) {
        return bookRepository.existsByName(name);
    }

    @Transactional
    public void remove(Integer id) {

        bookRepository.deleteById(id);
    }

    @Transactional
    public void update(Book book) {

        var forUpdate = bookRepository.getReferenceById(book.getId());
        forUpdate.setName(book.getName());
        forUpdate.setWriter(book.getWriter());
    }

    public List<Book> findAll() {
        //TODO don't forge pagination
        return bookRepository.findAll();
    }

    public String getService(){
        return "Book service";
    }
}
