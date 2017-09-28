package com.osiykm.flist.services;

import com.osiykm.flist.entities.Category;
import com.osiykm.flist.repositories.BookRepository;
import com.osiykm.flist.repositories.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class CategoryService {
    private final CategoryRepository categoryRepository;
    private final BookRepository bookRepository;

    @Autowired
    public CategoryService(CategoryRepository categoryRepository, BookRepository bookRepository) {
        this.categoryRepository = categoryRepository;
        this.bookRepository = bookRepository;
    }
    @Transactional
    public Category parseCategory(String name) {
        if (categoryRepository.existsByCode(genCode(name)))
            return categoryRepository.findByCode(genCode(name));

        return categoryRepository.save(Category.builder().name(name).code(genCode(name)).build());
    }

    String genCode(String name) {
        return Optional.of(name).orElseThrow(NullPointerException::new).replaceAll("[^A-Za-z _+.-]+", "").replaceAll(" ", "_").toLowerCase();
    }

    public void save(Set<Category> categories) {
        this.categoryRepository.save(categories);
    }

    @Transactional
    public void deleteByCode(String code) {
        Category category = categoryRepository.findByCode(code);
        bookRepository.save(
                bookRepository.findByCategoriesContaining(category).stream().peek(p -> p.getCategories().remove(category)).collect(Collectors.toSet())
        );
        categoryRepository.delete(category);
    }
}
