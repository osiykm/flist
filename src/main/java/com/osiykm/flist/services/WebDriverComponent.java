package com.osiykm.flist.services;

import lombok.Getter;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.io.Closeable;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

/***
 * @author osiykm
 * created 23.09.2017 22:37
 */
@Component
public class WebDriverComponent implements Closeable{
    private WebDriver driver;

    public WebDriverComponent() {
    }


    @Override
    public void close() throws IOException {
        driver.quit();
    }

    WebDriver getDriver() {
        if (driver == null) {
            try {
                this.driver = new RemoteWebDriver(new URL("http://localhost:4444/wd/hub"), DesiredCapabilities.phantomjs());
            } catch (MalformedURLException e) {
                e.printStackTrace();
            }
        }
        return driver;
    }
}
