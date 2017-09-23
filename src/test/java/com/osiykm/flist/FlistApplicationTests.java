package com.osiykm.flist;

import com.osiykm.flist.services.BookManageService;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.assertEquals;


@RunWith(SpringRunner.class)
@SpringBootTest
public class FlistApplicationTests {

    @Autowired
    private BookManageService service;
}
