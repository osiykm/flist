package com.osiykm.flist.services.parser;

import com.osiykm.flist.entities.Author;
import com.osiykm.flist.entities.Book;
import com.osiykm.flist.entities.Category;
import com.osiykm.flist.enums.BookStatus;
import com.osiykm.flist.services.CategoryService;
import com.osiykm.flist.services.WebDriverComponent;
import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

/***
 * @author osiykm
 * created 27.09.2017 23:59
 */
@Service
@Slf4j
public class FanfictionUrlParserService implements UrlParser {
    private WebDriver driver;
    private String cacheUrl = "";
    private long timer = Calendar.getInstance().getTimeInMillis();
    private final CategoryService categoryService;
    private final WebDriverComponent webDriverComponent;

    @Autowired
    public FanfictionUrlParserService(WebDriverComponent webDriverComponent, CategoryService categoryService) {
        this.categoryService = categoryService;
        this.webDriverComponent = webDriverComponent;
    }

    @Override
    public Book getBook(String url) {
        load(url);
        WebElement description;
        try {
            description = driver.findElement(By.xpath("//*[@id=\"profile_top\"]/span[4]"));
        } catch (Exception e) {
            description = driver.findElement(By.xpath("//*[@id=\"profile_top\"]/span[3]"));
        }

        String[] descriptions = description.getAttribute("innerText").split(" - ");
        return Book.builder()
                .name(driver.findElement(By.xpath("//*[@id=\"profile_top\"]/b")).getAttribute("innerText"))
                .status(Arrays.stream(descriptions).anyMatch(p -> p.startsWith("Status: ")) ? BookStatus.COMPLETED : BookStatus.UNPUBLISHED)
                .updated(new Date(description.findElements(By.tagName("span")).stream().map(p -> Long.valueOf(p.getAttribute("data-xutime"))).max(Long::compare).orElse(0L) * 1000L))
                .created(new Date(description.findElements(By.tagName("span")).stream().map(p -> Long.valueOf(p.getAttribute("data-xutime"))).min(Long::compare).orElse(0L) * 1000L))
                .description(driver.findElement(By.xpath("//*[@id=\"profile_top\"]/div")).getAttribute("innerText"))
                .url(url)
                .size(
                        Arrays.stream(descriptions)
                                .filter(p -> p.startsWith("Words: "))
                                .map(p -> Integer.parseInt(p.replace("Words: ", "").replace(",", "")))
                                .findFirst().orElse(0)
                )
                .chapters(
                        Arrays.stream(descriptions)
                                .filter(p -> p.startsWith("Chapters: "))
                                .map(p -> Integer.parseInt(p.replace("Chapters: ", "")))
                                .findFirst().orElse(1)
                )
                .build();
    }

    @Override
    public Author getAuthor(String url) {
        load(url);
        return Author.builder()
                .name(driver.findElement(By.xpath("//*[@id=\"profile_top\"]/a[1]")).getAttribute("innerText"))
                .url(driver.findElement(By.xpath("//*[@id=\"profile_top\"]/a[1]")).getAttribute("href"))
                .build();
    }

    @Override
    public Set<Category> getCategories(String url) {
        load(url);
        List<WebElement> elements = driver.findElements(By.xpath("//*[@id=\"pre_story_links\"]/span/a"));
        if (elements.size() == 2) {
            Set<Category> categories = new HashSet<>();
            categories.add(Category.builder().name(elements.get(1).getAttribute("innerText")).code(categoryService.genCode(elements.get(1).getAttribute("innerText"))).build());
            return categories;
        } else {
            driver.get(elements.get(0).getAttribute("href"));
            try {

                return driver.findElements(By.xpath("//*[@id=\"content_wrapper_inner\"]/a"))
                        .stream()
                        .filter(a -> !a.getAttribute("title").toLowerCase().equals("feed"))
                        .map(p -> Category.builder().code(categoryService.genCode(p.getAttribute("innerText"))).name(p.getAttribute("innerText")).build())
                        .collect(Collectors.toSet());
            } catch (Exception e) {
                return new HashSet<>();
            }
        }
    }

    private void load(String url) {
        if (driver == null)
            driver = webDriverComponent.getDriver();
        if (!this.cacheUrl.equals(url)) {
            if (Calendar.getInstance().getTimeInMillis() - timer < 500)
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    log.error("wrong wait period ", e);
                }
            driver.get(url);
        }
    }

    @Override
    public void unlock() {
        webDriverComponent.unlock();
        driver = null;
    }
}
