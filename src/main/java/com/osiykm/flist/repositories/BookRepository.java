package com.osiykm.flist.repositories;

import com.osiykm.flist.entities.Book;
import com.osiykm.flist.entities.Category;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RestResource;
import org.springframework.web.bind.annotation.RestController;

import java.util.Set;


public interface BookRepository extends PagingAndSortingRepository<Book, Long> {

    @RestResource(exported=false)
    Book findByUrl(String url);

    @RestResource(exported=false)
    Boolean existsByUrl(String url);

    @RestResource(exported = false)
    Set<Book> findByCategoriesContaining(Category category);
}
