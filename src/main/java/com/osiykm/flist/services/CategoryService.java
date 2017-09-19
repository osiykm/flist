package com.osiykm.flist.services;

import com.osiykm.flist.entities.Category;
import com.osiykm.flist.repositories.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
public class CategoryService {
    private final CategoryRepository categoryRepository;

    @Autowired
    public CategoryService(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    @Transactional
    public Category save(String name) {
        if(categoryRepository.existsByName(name))
            throw new RuntimeException("already_exist");
        return categoryRepository.save(new Category(name, name.toLowerCase().replace(" ", "_")));
    }
}
