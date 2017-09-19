package com.site;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

@Slf4j // simple logging facade for java
@Component
public class BookTestData {

    private final BookRepository bookRepository;
    private final DataSource dataSource;

    @Autowired
    public BookTestData(BookRepository bookRepository, DataSource dataSource) {
        this.bookRepository = bookRepository;
        this.dataSource = dataSource;
    }

    @PostConstruct
    public void initTestData() {
        bookRepository.save(new Book("C#", 1000));
        bookRepository.save(new Book("C and C++", 900));
        bookRepository.save(new Book("Java", 1100));
    }

    /*@PostConstruct
    public void initTestDataInOldFashionWay() {
        try(Connection connection = dataSource.getConnection()) {
            Statement statement = connection.createStatement();
            statement.executeUpdate("INSERT INTO Book (id, name, size) VALUES (4, 'Pascal', 800)");
        } catch (SQLException e) {
            log.error("Failed to init test");
        }
    }*/

}
