package com.osiykm.flist.services.programs;

import com.osiykm.flist.repositories.AuthorRepository;
import com.osiykm.flist.repositories.BookRepository;
import com.osiykm.flist.repositories.TaskRepository;
import com.osiykm.flist.services.CategoryService;
import com.osiykm.flist.services.UrlParserService;
import org.springframework.beans.factory.annotation.Autowired;

/***
 * @author osiykm
 * created 29.09.2017 0:35
 */
public class BookParserProgram implements Runnable {

    private final UrlParserService urlParserService;
    private final BookRepository bookRepository;
    private final CategoryService categoryService;
    private final AuthorRepository authorRepository;

    private boolean alive = false;

    @Autowired
    public BookParserProgram(UrlParserService urlParserService, BookRepository bookRepository, CategoryService categoryService, AuthorRepository authorRepository, TaskRepository taskRepository) {
        this.urlParserService = urlParserService;
        this.bookRepository = bookRepository;
        this.categoryService = categoryService;
        this.authorRepository = authorRepository;
    }
    
    @Override
    public void run() {

    }
}
