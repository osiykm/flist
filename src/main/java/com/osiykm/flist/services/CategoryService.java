package com.osiykm.flist.services;

import com.osiykm.flist.entities.Category;
import com.osiykm.flist.repositories.CategoryRepository;
import org.hibernate.bytecode.buildtime.spi.ExecutionException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Optional;

@Service
public class CategoryService {
    private final CategoryRepository categoryRepository;

    @Autowired
    public CategoryService(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    @Transactional
    public Category save(String name) {
        if(categoryRepository.existByCode(genCode(name)))
            throw new RuntimeException("already_exist");

        return categoryRepository.save(Category.builder().name(name).code(genCode(name)).build());
    }

    private String genCode(String name) {
        return Optional.of(name).orElseThrow(NullPointerException::new).replaceAll("[^A-Za-z _-]+", "").replaceAll(" ", "_").toLowerCase();
    }
}
