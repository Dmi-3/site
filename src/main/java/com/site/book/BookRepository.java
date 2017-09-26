package com.site.book;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

public interface BookRepository extends PagingAndSortingRepository<Book, Long> {

    Book findByName(String name);

    Book findByNameAndSize(String name, int size);

    @Query("select book from Book book where book.id=:id and book.name=:name")
    Book findByIdAndName(@Param("id") Long id, @Param("name") String name);


    @Query("select book from Book book where book.id=:id and book.size=:size")
    Book findByIdAndSize(@Param("id") Long id, @Param("size") int size);

    @Query("update book set book.name=:name, book.size=:size where book.id=:id ")
    void updateNameAndSize(@Param("name") String name, @Param("size") int size, @Param("id") Long id);
}
