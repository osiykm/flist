package com.osiykm.flist.services;

import com.osiykm.flist.entities.Book;
import lombok.Builder;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.springframework.stereotype.Service;

import javax.websocket.server.ServerEndpoint;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/***
 * @author osiykm
 * created 22.09.2017 21:16
 */
@Service
@Slf4j
public class BookStorageParserService {
    private WebDriver driver;


    public BookStorageParserService() {
        try {
            this.driver = new RemoteWebDriver(new URL("http://localhost:4444/wd/hub"), DesiredCapabilities.phantomjs());
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
    }

    public Book parseBook(URL url) {
        driver.get(url.toExternalForm());
//        response.put("name", driver.findElement(By.xpath("//*[@id=\"profile_top\"]/b")).getAttribute("innerText"));
//        response.put("author", driver.findElement(By.xpath("//*[@id=\"profile_top\"]/a[1]")).getAttribute("innerText"));
//        response.put("authorUrl", driver.findElement(By.xpath("//*[@id=\"profile_top\"]/a[1]")).getAttribute("href"));
//        response.put("description", driver.findElement(By.xpath("//*[@id=\"profile_top\"]/div")).getAttribute("innerText"));
//        List<WebElement> elements = driver.findElements(By.xpath("//*[@id=\"pre_story_links\"]/span/a"));
//        if (elements.size() == 2)
//            response.put("category", elements.get(1).getAttribute("innerText"));
//        else {
//            driver.get(elements.get(0).getAttribute("href"));
//            elements = driver.findElements(By.xpath("//*[@id=\"content_wrapper_inner\"]/a"));
//            log.info("Size: " + elements.size());
//            elements = elements.stream().filter(a -> !a.getAttribute("title").toLowerCase().equals("feed")).collect(Collectors.toList());
//            for (int i = 0; i < elements.size(); i++) {
//                response.put("category" + (i + 1), elements.get(i).getAttribute("innerText"));
//            }
//        }
        log.info("parsed book:" );
        return null;
    }
}
