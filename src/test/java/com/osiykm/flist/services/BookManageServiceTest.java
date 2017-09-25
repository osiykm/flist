package com.osiykm.flist.services;

import com.osiykm.flist.entities.Author;
import com.osiykm.flist.entities.Book;
import com.osiykm.flist.entities.Category;
import com.osiykm.flist.repositories.AuthorRepository;
import com.osiykm.flist.repositories.BookRepository;
import com.osiykm.flist.repositories.CategoryRepository;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.net.URL;

import static org.mockito.Matchers.anyObject;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.when;

/***
 * @author osiykm
 * created 23.09.2017 22:43
 */
public class BookManageServiceTest {

    private BookManageService service;

    @Mock
    private BookRepository bookRepository;

    @Mock
    private CategoryRepository mockRepository;

    private CategoryService categoryService;

    @Mock
    private AuthorRepository authorRepository;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        categoryService = new CategoryService(mockRepository, null);
        service = new BookManageService(new WebDriverService(), bookRepository, categoryService, authorRepository);
        when(mockRepository.save((Category) anyObject())).then(p -> p.getArguments()[0]);
        when(authorRepository.findByName(anyString())).then(p -> null);
        when(authorRepository.save((Author) anyObject())).then(p -> p.getArguments()[0]);
        when(bookRepository.save((Book) anyObject())).then(p -> p.getArguments()[0]);
    }
    @Test
    public void parseBook() throws Exception {
        Book book = service.parseBook("https://www.fanfiction.net/s/12582017/1/Daily-life");
        System.out.println("BOOOK "+book);

    }

}