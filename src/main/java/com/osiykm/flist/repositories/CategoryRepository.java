package com.osiykm.flist.repositories;

import com.osiykm.flist.entities.Category;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RestResource;


public interface CategoryRepository extends CrudRepository<Category, Long> {

    @RestResource(exported=false)
    Category findByCode(String code);

    @RestResource(exported=false)
    Boolean existsByCode(String name);
}
