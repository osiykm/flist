package com.osiykm.flist.repositories;

import com.osiykm.flist.entities.Book;
import com.osiykm.flist.enums.BookStatus;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RestResource;

import java.util.List;


public interface BookRepository extends PagingAndSortingRepository<Book, Long> {

    @RestResource(exported = false)
    Book findByUrl(String url);

    @RestResource(exported = false)
    Boolean existsByUrl(String url);


    @RestResource(exported = false)
    List<Book> findByStatusNot(BookStatus status);

    @RestResource(rel = "status", path = "status")
    List<Book> findByStatus(@Param("status") BookStatus status);
}
