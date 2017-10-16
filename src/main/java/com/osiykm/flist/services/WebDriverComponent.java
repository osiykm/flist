package com.osiykm.flist.services;

import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.springframework.stereotype.Component;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/***
 * @author osiykm
 * created 23.09.2017 22:37
 */
@Component
@Slf4j
public class WebDriverComponent {
    private WebDriver driver;
    private Lock lock;

    public WebDriverComponent() {
        lock = new ReentrantLock();
    }


    public void unlock() {
        lock.unlock();
    }

    public WebDriver getDriver() {
        if (driver == null) {
            try {
                this.driver = new RemoteWebDriver(new URL("http://localhost:4444/wd/hub"), DesiredCapabilities.phantomjs());
            } catch (MalformedURLException e) {
                log.error("error: ", e);
                return null;
            }
        }
        lock.lock();
        return driver;
    }
}
