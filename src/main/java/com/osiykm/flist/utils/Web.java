package com.osiykm.flist.utils;

import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.phantomjs.PhantomJSDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.io.Closeable;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;


@Slf4j
@Component
@Scope("prototype")
public class Web implements Closeable {
    private WebDriver driver;

    public Web() {
    }

    @Override
    public void close() throws IOException {
        driver.quit();
    }
    public void init() {
        try {
            driver = new RemoteWebDriver(new URL("http://localhost:4444/wd/hub"), DesiredCapabilities.chrome());
        } catch (MalformedURLException e) {
            log.error(e.getMessage());
        }
    }
    public void run() {
        driver.get("http://google.com");
    }

}
