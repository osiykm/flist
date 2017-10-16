package com.osiykm.flist.services.programs;

import com.osiykm.flist.entities.Author;
import com.osiykm.flist.entities.Book;
import com.osiykm.flist.entities.Category;
import com.osiykm.flist.entities.Task;
import com.osiykm.flist.enums.TaskStatus;
import com.osiykm.flist.repositories.AuthorRepository;
import com.osiykm.flist.repositories.BookRepository;
import com.osiykm.flist.repositories.TaskRepository;
import com.osiykm.flist.services.CategoryService;
import com.osiykm.flist.services.parser.FanfictionUrlParserService;
import com.osiykm.flist.services.parser.SBUrlParserService;
import com.osiykm.flist.services.parser.SVUrlParserService;
import com.osiykm.flist.services.parser.UrlParser;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;
import java.util.*;

/***
 * @author osiykm
 * created 29.09.2017 0:35
 */
@Component
@Slf4j
public class BookParserProgram extends BaseProgram {

    private final BookRepository bookRepository;
    private final CategoryService categoryService;
    private final AuthorRepository authorRepository;
    private final TaskRepository taskRepository;

    private Map<String, UrlParser> parsers = new HashMap<>();
    @Autowired
    public BookParserProgram(FanfictionUrlParserService fanfictionUrlParserService,
                             BookRepository bookRepository,
                             CategoryService categoryService,
                             AuthorRepository authorRepository,
                             TaskRepository taskRepository,
                             SBUrlParserService sbUrlParserService,
                             SVUrlParserService svUrlParserService) {
        this.bookRepository = bookRepository;
        this.categoryService = categoryService;
        this.authorRepository = authorRepository;
        this.taskRepository = taskRepository;
        parsers.put("fanfiction", fanfictionUrlParserService);
        parsers.put("spacebattles", sbUrlParserService);
        parsers.put("sufficientvelocity", svUrlParserService);
    }

    @Override
    public void run() {
        UrlParser urlParser;
        log.info("Start Parser");
        List<Task> tasks = taskRepository.findByStatus(TaskStatus.WAITING);
        for (Task task :
                tasks) {
            if (isAlive()) {
                try {
                    log.info(task.getUrl() + " start");
                    urlParser = parsers.get(getSite(task.getUrl()));
                    save(
                            urlParser.getBook(task.getUrl()),
                            urlParser.getAuthor(task.getUrl()),
                            urlParser.getCategories(task.getUrl())
                    );
                    taskRepository.save(task.setStatus(TaskStatus.COMPLETED));
                    log.info(task.getUrl() + " save");
                    urlParser.unlock();
                } catch (Exception e) {
                    log.info("task " + task.toString() + " error ", e);
                    taskRepository.save(task.setStatus(TaskStatus.ERROR));
                }
            } else {
                break;
            }
        }
        log.info("Stop parser");
        stop();
    }

    private String getSite(String url) {
        return url.split("/")[2].split("\\.")[1];
    }

    @Transactional
    void save(Book book, Author author, Set<Category> categories) {
        log.info("START save book " + book.toString());
        log.info("author " + author);
        log.info("categories " + categories);
        Author newAuthor = authorRepository.findByUrl(author.getUrl());
        bookRepository.save(
                book
                        .setAuthor(
                                Optional.ofNullable(newAuthor)
                                        .orElseGet(() -> authorRepository.save(author)))
                        .setCategories(categoryService.save(categories))
        );
    }
}
