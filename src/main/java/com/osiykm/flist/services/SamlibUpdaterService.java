package com.osiykm.flist.services;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/***
 * @author osiykm
 * created 29.09.2017 15:14
 */
@Service
@Slf4j
public class SamlibUpdaterService {

    private final WebDriver driver;
    private Properties properties = new Properties();

    {
        try(InputStream stream = new FileInputStream(new File("samlib.properties"))) {
            properties.load(stream);
        } catch (IOException e1) {
            log.error(e1.getMessage());
        }
    }

    @Autowired
    public SamlibUpdaterService(WebDriverComponent webDriverComponent) {
        this.driver = webDriverComponent.getDriver();
    }

    private void login() {
        try {
            driver.findElement(By.xpath("/html/body/center[1]/nobr/a[2]/font"));
        } catch (Exception e) {

            String cache = driver.getCurrentUrl();
            driver.get("http://samlib.ru/cgi-bin/login");
            driver.findElement(By.xpath("/html/body/form/div/table[2]/tbody/tr[2]/td[2]/input")).sendKeys(properties.getProperty("login"));
            driver.findElement(By.xpath("/html/body/form/div/table[2]/tbody/tr[3]/td[2]/input")).sendKeys(properties.getProperty("password"));
            driver.findElement(By.xpath("/html/body/form/div/table[2]/tbody/tr[3]/td[3]/input")).submit();
            sleep();
            driver.get(cache);
        }
    }

    void listUpdate(String annotation, String text) {
        login();
        annotationUpdate(annotation);
        textUpdate(text);
    }

    private void annotationUpdate(String annotation) {
        driver.get("http://samlib.ru/cgi-bin/zhurnal?OPERATION=edit_book&FILE="+properties.getProperty("fanfics-list")+"&DIR="+properties.getProperty("samlib-dir"));
        driver.findElement(By.xpath("/html/body/li[3]/div[1]/table/tbody/tr/td/center/table/tbody/tr[9]/td[3]/textarea")).clear();
        driver.findElement(By.xpath("/html/body/li[3]/div[1]/table/tbody/tr/td/center/table/tbody/tr[9]/td[3]/textarea")).sendKeys(annotation);
        driver.findElement(By.xpath("/html/body/li[3]/div[1]/table/tbody/tr/td/center/table/tbody/tr[10]/td[2]/center/input[1]")).click();
        sleep();
    }
    private void textUpdate(String text) {
        driver.get("http://samlib.ru/cgi-bin/zhurnal?OPERATION=(Text+edit)&DIR="+properties.getProperty("samlib-dir")+"&STORE_FILE="+properties.getProperty("fanfics-list"));
        driver.findElement(By.xpath("/html/body/div[2]/table/tbody/tr/td[1]/form/table/tbody/tr[1]/td/small/textarea")).clear();
        driver.findElement(By.xpath("/html/body/div[2]/table/tbody/tr/td[1]/form/table/tbody/tr[1]/td/small/textarea")).sendKeys(text);
        driver.findElement(By.xpath("/html/body/div[2]/table/tbody/tr/td[1]/form/table/tbody/tr[2]/td/input")).click();
        sleep();
    }
    private void sleep() {
        try {
            Thread.sleep(500);
        } catch (InterruptedException ignored) {}
    }
}
