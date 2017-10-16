package com.osiykm.flist.services.parser;

import com.osiykm.flist.entities.Author;
import com.osiykm.flist.entities.Book;
import com.osiykm.flist.entities.Category;
import com.osiykm.flist.services.WebDriverComponent;
import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.WebDriver;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Calendar;
import java.util.Set;

/***
 * @author osiykm
 * created 16.10.2017 13:42
 */
@Slf4j
public abstract class UrlParser {
    WebDriver driver;
    private String cacheUrl = "";
    private long timer = Calendar.getInstance().getTimeInMillis();

    private final WebDriverComponent webDriverComponent;

    @Autowired
    public UrlParser(WebDriverComponent webDriverComponent) {
        this.webDriverComponent = webDriverComponent;
    }

    public abstract Book getBook(String url);

    public abstract Author getAuthor(String url);

    public abstract Set<Category> getCategories(String url);

    public final void unlock() {
        webDriverComponent.unlock();
        driver = null;
    }

    final void load(String url) {
        log.info("LOAD URL: " + url);
        if (driver == null)
            driver = webDriverComponent.getDriver();
        if (!this.cacheUrl.equals(url)) {
            if (Calendar.getInstance().getTimeInMillis() - timer < 500)
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    log.error("wrong wait period ", e);
                }
            driver.get(url);
            timer = Calendar.getInstance().getTimeInMillis();
        }
    }

    int parseNumber(String s) {
        log.info("parseNumber: " + s);
        int size = 1;
        switch (s.substring(s.length() - 1, s.length())) {
            case "m":
                size *= 1000_000;
                break;
            case "k":
                size *= 1000;
                break;
            default:
                s = s + "n";
        }
        s = s.substring(0, s.length() - 1);

        if (s.contains(".")) {
            size /= 10 * s.split("\\.")[1].length();
            s = s.replace(".", "");
        }
        size *= Integer.parseInt(s);
        log.info("size: " + size);
        return size;
    }
}
