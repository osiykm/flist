package com.osiykm.flist.repositories;

import com.osiykm.flist.entities.Category;
import org.springframework.data.repository.CrudRepository;


public interface CategoryRepository extends CrudRepository<Category, Long> {
    Category findByCode(String code);
    Boolean existsByName(String name);
}
