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
import com.osiykm.flist.services.UrlParserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;
import java.util.Set;

/***
 * @author osiykm
 * created 29.09.2017 0:35
 */
@Component
@Slf4j
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
        log.info("Start Parser");
        List<Task> tasks = taskRepository.findByStatus(TaskStatus.WAITING);
        for (Task task :
                tasks) {
            if (isAlive()) {
                try {
                    log.info(task.getUrl() + " start");
                    save(
                            urlParserService.getBook(task.getUrl()),
                            urlParserService.getAuthor(task.getUrl()),
                            urlParserService.getCategories(task.getUrl())
                    );
                    taskRepository.save(task.setStatus(TaskStatus.COMPLETED));
                    log.info(task.getUrl() + " save");
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
        urlParserService.unlock();
    }

    @Transactional
    void save(Book book, Author author, Set<Category> categories) {
        log.info("START save book " + book.toString());
        log.info("author " + author);
        log.info("categories " + categories);
        bookRepository.save(
                book
                        .setAuthor(
                                Optional.ofNullable(authorRepository.findByName(author.getName()))
                                        .orElse(authorRepository.save(author)))
                        .setCategories(categoryService.save(categories))
        );
    }
}
