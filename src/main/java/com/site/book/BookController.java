package com.site.book;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@RestController
/** эквивалентно аннотациям @Controller, @ResponseBody: */
public class BookController {

    private final BookRepository bookRepository;

    @Autowired //  автоматический поиск и присвоение
    public BookController(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    @RequestMapping(value = "/booksUniqueCreate", method = RequestMethod.POST)
    // to map URLs to controller methods in different ways
    public void create(@RequestBody Book book) {
        Book bookDuplicate = bookRepository.findByName(book.getName());
        if (bookDuplicate != null)
            throw new RuntimeException("Found duplicate during exception");
        bookRepository.save(book);
    }

    @RequestMapping(value = "/bookDelete", method = RequestMethod.DELETE)
    public void delete(@RequestBody Book book) { // value works as a parameter
        if (!bookRepository.exists(book.getId()))
            throw new RuntimeException("The book does not exist!");
        bookRepository.delete(book.getId());
    }

    @RequestMapping(value = "/bookUniqueUpdate", method = RequestMethod.POST)
    public void update(@RequestBody Book book) { // value works as a parameter
        if (!bookRepository.exists(book.getId()))
            throw new RuntimeException("The book does not exist!");
        bookRepository.updateNameAndSize(book.getName(), book.getSize(), book.getId());
    }

    @RequestMapping(value = "/books/lite", method = RequestMethod.GET)
    public List<Book> get() {
        List<Book> result = new ArrayList<>();

        bookRepository.findAll().forEach(book -> result.add(book));
        //bookRepository.findAll().forEach(result::add);
        return result;
    }

    @RequestMapping(value = "/books/{id}/{name}", method = RequestMethod.GET)
    public Book getByIdAndName(@PathVariable("id") Long id, @PathVariable String name) {
        return bookRepository.findByIdAndName(id, name);

    }
}
