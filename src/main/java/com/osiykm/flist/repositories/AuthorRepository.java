package com.osiykm.flist.repositories;

import com.osiykm.flist.entities.Author;
import org.springframework.data.repository.CrudRepository;

public interface AuthorRepository extends CrudRepository<Author, Long> {
}
