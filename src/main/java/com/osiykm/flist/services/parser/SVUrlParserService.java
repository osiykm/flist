package com.osiykm.flist.services.parser;

import com.osiykm.flist.entities.Author;
import com.osiykm.flist.entities.Book;
import com.osiykm.flist.entities.Category;
import com.osiykm.flist.services.WebDriverComponent;
import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.stream.Collectors;

/***
 * @author osiykm
 * created 16.10.2017 21:47
 */
@Slf4j
@Service
public class SVUrlParserService extends UrlParser{
    private final static SimpleDateFormat format = new SimpleDateFormat("MMM dd, yyyy", Locale.ENGLISH);

    @Autowired
    public SVUrlParserService(WebDriverComponent webDriverComponent) {
        super(webDriverComponent);
    }

    @Override
    public Book getBook(String url) {
        url = fixUrl(url);
        return Book.builder()
                .name(parseName(url))
                .url(url)
                .created(parseCreated(url))
                .chapters(parseChapters(url))
                .updated(parseUpdated(url))
                .size(parseSize(url))
                .description("")
                .build();
    }

    @Override
    public Author getAuthor(String url) {
        url = fixUrl(url);
        load(url);
        WebElement element = driver.findElement(By.xpath("//*[@id=\"pageDescription\"]/a[2]"));
        return Author.builder().name(element.getAttribute("innerText")).url(element.getAttribute("href")).build();
    }

    @Override
    public Set<Category> getCategories(String url) {
        return new HashSet<>();
    }

    private Integer parseSize(String url) {
        Set<String> urls = parseThreads();
        int size = 0;
        for (String p : urls) {
            load(p);
            size += parseNumber(driver.findElement(By.xpath("//*[@id=\"content\"]/div/div/div/div/div[2]/div[3]/div[1]/div/span[2]"))
                    .getAttribute("innerText")
                    .split(",")[1]
                    .replace(" Word Count: ", ""));
        }
        return size;
    }

    private Date parseUpdated(String url) {
        load(url + "/threadmarks");
        Set<String> urls = parseThreads();
        List<Long> dates = new ArrayList<>();
        for (String thread_url :
                urls) {
            load(thread_url);
            try {
                dates.add(
                        driver.findElements(By.xpath("//*[@id=\"content\"]/div/div/div/div/div[2]/div[3]/div[2]/ol/li[*]/div/abbr"))
                                .stream()
                                .mapToLong(p -> Long.parseLong(p.getAttribute("data-time")) * 1000L)
                                .max()
                                .orElse(0)
                );
            } catch (Exception e) {
                dates.add(
                        driver.findElements(By.xpath("//*[@id=\"content\"]/div/div/div/div/div[2]/div[3]/div[2]/ol/li[*]/div/span"))
                                .stream().mapToLong(p -> parse(p.getAttribute("innerText")).getTime()).max().orElse(0)

                );
            }
        }
        log.info(String.valueOf(dates));
        return new Date(dates.stream().mapToLong(p -> p).max().orElse(0));
    }

    private Integer parseChapters(String url) {
        Set<String> urls = parseThreads();
        int chapters = 0;
        for (String p : urls) {
            load(p);
            chapters += driver.findElements(By.xpath("//*[@id=\"content\"]/div/div/div/div/div[2]/div[3]/div[2]/ol/li"))
                    .size();

            log.info("CURRENT CHAPTERS: " + chapters);
        }
        return chapters;
    }

    private Date parseCreated(String url) {
        load(url + "/threadmarks");
        String date = driver.findElement(By.xpath("//*[@id=\"content\"]/div/div/div/div/div[2]/div[3]/div[2]/ol/li[1]/div/span")).getAttribute("innerText");
        try {
            return format.parse(date);
        } catch (ParseException e) {
            return null;
        }
    }

    private String parseName(String url) {
        load(url);
        return driver.findElement(By.xpath("//*[@id=\"content\"]/div/div/div/div/div[2]/div[2]/h1")).getAttribute("innerText");
    }


    private String fixUrl(String url) {
        return Arrays.stream(url.split("/")).limit(5).collect(Collectors.joining("/"));
    }

    private static Date parse(String date) {
        try {
            return format.parse(date);
        } catch (ParseException e) {
            return new Date(0);
        }
    }

    private Set<String> parseThreads() {
        Set<String> urls = driver.findElements(By.xpath("//*[@id=\"content\"]/div/div/div/div/div[2]/div[3]/ol/li[*]/h4/a"))
                .stream()
                .map(p -> p.getAttribute("href"))
                .collect(Collectors.toSet());
        log.info(String.valueOf(urls));
        return urls;
    }
}
