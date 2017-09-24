package com.osiykm.flist.services;

import com.osiykm.flist.entities.Author;
import com.osiykm.flist.entities.Book;
import com.osiykm.flist.entities.Category;
import com.osiykm.flist.enums.BookStatus;
import com.osiykm.flist.repositories.AuthorRepository;
import com.osiykm.flist.repositories.BookRepository;
import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.actuate.autoconfigure.ShellProperties;
import org.springframework.stereotype.Service;

import java.net.URL;
import java.util.*;
import java.util.stream.Collectors;

/***
 * @author osiykm
 * created 22.09.2017 21:16
 */
@Service
@Slf4j
public class BookManageService {

    private final WebDriverService driverService;
    private final BookRepository bookRepository;
    private final CategoryService categoryService;
    private final AuthorRepository authorRepository;


    @Autowired
    public BookManageService(WebDriverService driverService, BookRepository bookRepository, CategoryService categoryService, AuthorRepository authorRepository) {
        this.driverService = driverService;
        this.bookRepository = bookRepository;
        this.categoryService = categoryService;
        this.authorRepository = authorRepository;
    }

    public Book parseBook(URL url) {
        WebDriver driver = driverService.getDriver();
        driver.get(url.toExternalForm());
        WebElement description = driver.findElement(By.xpath("//*[@id=\"profile_top\"]/span[4]"));
        String[] descriptions = description.getAttribute("innerText").split(" - ");
        Book book = Book.builder()
                .name(driver.findElement(By.xpath("//*[@id=\"profile_top\"]/b")).getAttribute("innerText"))
                .status(Arrays.stream(descriptions).anyMatch(p -> p.startsWith("Status: ")) ? BookStatus.COMPLETED : BookStatus.UNPUBLISHED)
                .updated(new Date(description.findElements(By.tagName("span")).stream().map(p -> Long.valueOf(p.getAttribute("data-xutime"))).max(Long::compare).orElse(0L) * 1000L))
                .created(new Date(description.findElements(By.tagName("span")).stream().map(p -> Long.valueOf(p.getAttribute("data-xutime"))).min(Long::compare).orElse(0L) * 1000L))
                .description(driver.findElement(By.xpath("//*[@id=\"profile_top\"]/div")).getAttribute("innerText")).build();
        log.info("current book: " + book);
        String authorName = driver.findElement(By.xpath("//*[@id=\"profile_top\"]/a[1]")).getAttribute("innerText");
        Author author = Optional
                .ofNullable(authorRepository.findByName(authorName))
                .orElse(authorRepository.save(
                        Author.builder()
                                .name(authorName)
                                .url(driver.findElement(By.xpath("//*[@id=\"profile_top\"]/a[1]")).getAttribute("href"))
                                .build()));
        List<Category> categories = new ArrayList<>();
        List<WebElement> elements = driver.findElements(By.xpath("//*[@id=\"pre_story_links\"]/span/a"));
        if (elements.size() == 2)
            categories.add(categoryService.parseCategory(elements.get(1).getAttribute("innerText")));
        else {
            driver.get(elements.get(0).getAttribute("href"));
            categories = driver.findElements(By.xpath("//*[@id=\"content_wrapper_inner\"]/a"))
                    .stream()
                    .filter(a -> !a.getAttribute("title").equals("feed"))
                    .map(p -> categoryService.parseCategory(p.getAttribute("innerText")))
                    .collect(Collectors.toList());
        }
        book.setAuthor(author);
        book.setCategories(categories);
        return bookRepository.save(book);
    }
}
