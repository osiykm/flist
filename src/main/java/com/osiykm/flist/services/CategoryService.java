package com.osiykm.flist.services;

import com.osiykm.flist.entities.Category;
import com.osiykm.flist.repositories.BookRepository;
import com.osiykm.flist.repositories.CategoryRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@Slf4j
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

    public String genCode(String name) {
        return Optional.ofNullable(name).orElseThrow(NullPointerException::new)
                .toLowerCase()
                .replaceAll("[^a-z _+./-]+", "")
                .replace(" ", "_")
                .replace("/", "-")
                .replaceAll("[_-]+$|^[_-]+", "");

    }

    public Set<Category> save(Set<Category> categories) {
        log.info("categorise = " + categories);
        return categories.stream()
                .map(p -> Optional.ofNullable(
                        categoryRepository.findByCode(p.getCode())).orElseGet(() -> saveOne(p)))
                .collect(Collectors.toSet());
    }

    private Category saveOne(Category category) {
        return categoryRepository.save(category);
    }

    @Transactional
    public void deleteByCode(String code) {
        Category category = categoryRepository.findByCode(code);
        bookRepository.save(
                category.getBooks().stream().peek(p -> p.getCategories().remove(category)).collect(Collectors.toSet())
        );
        categoryRepository.delete(category);
    }
}
