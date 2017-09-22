package com.osiykm.flist;

import com.osiykm.flist.services.BookStorageParserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.FilterType;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.junit4.SpringRunner;

import java.net.MalformedURLException;
import java.net.URL;

import static org.junit.Assert.assertEquals;


@SpringBootTest
@ComponentScan(basePackages = "com.osiykm.flist.services", useDefaultFilters = false, includeFilters = {
     @ComponentScan.Filter(type = FilterType.ASSIGNABLE_TYPE, value = {BookStorageParserService.class})
})
public class FlistApplicationTests {

    @Autowired
    private BookStorageParserService service;

    @Test
    public void simoleTest() throws MalformedURLException {
        service.parseBook(new URL("https://www.fanfiction.net/s/12406769/1/FateBlood-Reign"));
    }
}
