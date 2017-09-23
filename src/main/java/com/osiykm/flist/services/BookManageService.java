package com.osiykm.flist.services;

import com.osiykm.flist.entities.Book;
import com.osiykm.flist.repositories.AuthorRepository;
import com.osiykm.flist.repositories.BookRepository;
import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.net.URL;

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
        WebElement descriptuion;
//        Book.builder().name(driver.findElement(By.xpath("//*[@id=\"profile_top\"]/b")).getAttribute("innerText")).status(BookStatus.UNPUBLISHED).;
//        response.put("author", driver.findElement(By.xpath("//*[@id=\"profile_top\"]/a[1]")).getAttribute("innerText"));
//        response.put("authorUrl", driver.findElement(By.xpath("//*[@id=\"profile_top\"]/a[1]")).getAttribute("href"));
//        response.put("description", driver.findElement(By.xpath("//*[@id=\"profile_top\"]/div")).getAttribute("innerText"));
//        List<WebElement> elements = driver.findElements(By.xpath("//*[@id=\"pre_story_links\"]/span/a"));
//        if (elements.size() == 2)
//            response.put("category", elements.get(1).getAttribute("innerText"));
//        else {
//            driver.get(elements.get(0).getAttribute("href"));
//            elements = driver.findElements(By.xpath("//*[@id=\"content_wrapper_inner\"]/a"));
//            response.put("Size", String.valueOf(elements.size()));
//            elements = elements.stream().filter(a -> !a.getAttribute("title").toLowerCase().equals("feed")).collect(Collectors.toList());
//            for (int i = 0; i < elements.size(); i++) {
//                response.put("category" + (i + 1), elements.get(i).getAttribute("innerText"));
//            }
//
//        }
//        String resp = ).getAttribute("innerText");
//        response.put("test", resp);
//        log.info("parsed book:" + response);
        return null;
    }
}
