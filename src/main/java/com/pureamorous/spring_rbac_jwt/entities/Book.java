package com.pureamorous.spring_rbac_jwt.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@Table(name = "books")
@NoArgsConstructor
@AllArgsConstructor
public class Book {
    @Id
    @GeneratedValue
    @Column(name = "book_id")
    private Integer id;

    @Column(name = "book_name")
    private String name;

    @Column(name = "book_writer")
    private String writer;
}
