package com.osiykm.flist.controllers;

import com.osiykm.flist.utils.Web;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.RepositoryRestController;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.validation.constraints.NotNull;
import java.io.IOException;

@RepositoryRestController
@Slf4j
public class BookController {
    private final Web web;

    @Autowired
    public BookController(Web web) {
        this.web = web;
    }

    @RequestMapping(value = "/books/autoCreate", method = RequestMethod.GET)
    public void autoCreate() {
        log.info("START LOG");
        web.init();
        web.run();
        try {
            web.close();
        } catch (IOException e) {
            log.error("", e);
        }
    }
}

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
class UrlRequest {
    @NotNull
    private String url;
}
