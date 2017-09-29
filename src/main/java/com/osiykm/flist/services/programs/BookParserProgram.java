package com.osiykm.flist.services.programs;

import com.osiykm.flist.repositories.AuthorRepository;
import com.osiykm.flist.repositories.BookRepository;
import com.osiykm.flist.repositories.TaskRepository;
import com.osiykm.flist.services.CategoryService;
import com.osiykm.flist.services.UrlParserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/***
 * @author osiykm
 * created 29.09.2017 0:35
 */
@Component
public class BookParserProgram extends BaseProgram {

    private final UrlParserService urlParserService;
    private final BookRepository bookRepository;
    private final CategoryService categoryService;
    private final AuthorRepository authorRepository;
    private final TaskRepository taskRepository;

    @Autowired
    public BookParserProgram(UrlParserService urlParserService, BookRepository bookRepository, CategoryService categoryService, AuthorRepository authorRepository, TaskRepository taskRepository) {
        this.urlParserService = urlParserService;
        this.bookRepository = bookRepository;
        this.categoryService = categoryService;
        this.authorRepository = authorRepository;
        this.taskRepository = taskRepository;
    }
    
    @Override
    public void run() {

    }
}
