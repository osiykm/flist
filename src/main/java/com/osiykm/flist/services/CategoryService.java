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
        return Optional.ofNullable(name).orElseThrow(NullPointerException::new)
                .toLowerCase()
                .replaceAll("[^a-z _+./-]+", "")
                .replace(" ", "_")
                .replace("/", "-")
                .replaceAll("[_-]+$|^[_-]+", "");

    }

    public Set<Category> save(Set<Category> categories) {
        return categories.stream()
                .map(p ->
                        Optional.ofNullable(
                                categoryRepository.findByCode(p.getCode()))
                                .orElse(categoryRepository.save(p)))
                .collect(Collectors.toSet());
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
