package com.site.book;

import lombok.Data;
import org.springframework.hateoas.Identifiable;

import javax.persistence.*;

@Data // auto getter/setter (into targer)
@Entity
@Table(name = "Book")
public class Book implements Identifiable<Long> {

    @Id
    @GeneratedValue // strategy
    private Long id;

    @Column
    private String name;

    @Column
    private int size;

    public Book() {
    }

    public Book(String name, int size) {
        this.name = name;
        this.size = size;
    }

    public String getName() {
        return name;
    }

    @Override
    public Long getId() {
        return id;
    }
}
