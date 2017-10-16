package com.osiykm.flist.repositories;

import com.osiykm.flist.entities.Author;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RestResource;


public interface AuthorRepository extends CrudRepository<Author, Long> {

    @RestResource(exported = false)
    Author findByUrl(String url);
}
