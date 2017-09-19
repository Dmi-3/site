package com.site;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@RestController
public class BookController {

    private final BookRepository bookRepository;

    @Autowired //  filling immediately (after creation)
    public BookController(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    @RequestMapping(value = "/booksUniqueCreate", method = RequestMethod.POST) // to map URLs to controller methods in different ways
    public void create(@RequestBody Book book) {
        Book bookDuplicate = bookRepository.findByName(book.getName());
        if (bookDuplicate != null)
            throw new RuntimeException("Found duplicate during exception");
        bookRepository.save(book);
    }

    @RequestMapping(value = "/books/list", method = RequestMethod.GET)
    public List<Book> get() {
        List<Book> result = new ArrayList<>();
        bookRepository.findAll().forEach(result::add);
        return result;
    }

    @RequestMapping(value = "/books/{id}/{name}", method = RequestMethod.GET)
    public Book getByIdAndName(@PathVariable("id") Long id, @PathVariable String name) {
        return bookRepository.findByIdAndName(id, name);

    }

    @RequestMapping(value = "/books/delete/{id}", method = RequestMethod.DELETE)
    public void delete(@PathVariable("id") Long id) { // value works as a parameter
        Book book = bookRepository.findOne(id);
        if (book != null) {
            bookRepository.delete(id);
        }
        else throw new RuntimeException("The Book does not exist");
    }
}
