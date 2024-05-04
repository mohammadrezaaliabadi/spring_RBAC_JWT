package com.pureamorous.spring_rbac_jwt.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

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

    @NotBlank(message = "Book name can't be blank")
    @Column(name = "book_name")
    private String name;

    @NotBlank(message = "book writer can't be blank")
    @Column(name = "book_writer")
    private String writer;
}
