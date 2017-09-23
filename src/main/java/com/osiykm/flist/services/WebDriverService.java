package com.osiykm.flist.services;

import lombok.Getter;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.springframework.stereotype.Service;

import java.io.Closeable;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

/***
 * @author osiykm
 * created 23.09.2017 22:37
 */
@Service
@Getter
public class WebDriverService implements Closeable{
    private WebDriver driver;

    public WebDriverService() {
        try {
            this.driver = new RemoteWebDriver(new URL("http://localhost:4444/wd/hub"), DesiredCapabilities.phantomjs());
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
    }


    @Override
    public void close() throws IOException {
        driver.quit();
    }
}
