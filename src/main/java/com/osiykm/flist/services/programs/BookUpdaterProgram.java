package com.osiykm.flist.services.programs;

import com.osiykm.flist.entities.Book;
import com.osiykm.flist.enums.BookStatus;
import com.osiykm.flist.repositories.BookRepository;
import com.osiykm.flist.services.parser.FanfictionUrlParserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/***
 * @author osiykm
 * created 28.09.2017 22:49
 */
@Component
@Slf4j
public class BookUpdaterProgram extends BaseProgram {
    private final FanfictionUrlParserService urlParserService;
    private final BookRepository bookRepository;


    @Autowired
    public BookUpdaterProgram(FanfictionUrlParserService urlParserService, BookRepository bookRepository) {
        this.urlParserService = urlParserService;
        this.bookRepository = bookRepository;
    }

    @Override
    public void run() {
        List<Book> books;
        List<Book> updatedBooks;
        log.info("Start update books");
        books = bookRepository.findByStatusNot(BookStatus.COMPLETED);
        log.info("find " + books.size() + "books for update");
        updatedBooks = new ArrayList<>();
        for (Book book :
                books) {
            if (isAlive()) {
                Book updatedBook = urlParserService.getBook(book.getUrl());
                updatedBooks.add(book.setSize(updatedBook.getSize()).setChapters(updatedBook.getChapters()).setStatus(updatedBook.getStatus()));
            } else {
                break;
            }
        }
        log.info("updated " + updatedBooks.size() + " books");
        bookRepository.save(updatedBooks);
        stop();
    }
}
