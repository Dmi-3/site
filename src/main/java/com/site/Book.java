package com.site;

import lombok.Data;
import org.springframework.hateoas.Identifiable;

import javax.persistence.*;

@Data
@Entity
@Table(name="Book")
public class Book implements Identifiable<Long> {

    @Id
    @GeneratedValue
    private Long id;

    @Column
    private String name;

    @Column
    private int size;

    public Book(){};

    public Book(String name, int size) {
        this.name = name;
        this.size = size;
    }

    @Override
    public Long getId() {
        return id;
    }

}
