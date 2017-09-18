package com.osiykm.flist.controllers;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.rest.webmvc.RepositoryRestController;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.validation.constraints.NotNull;

@RepositoryRestController
public class BookController {

    @RequestMapping(value = "/books/autoCreate", method = RequestMethod.POST)
    public void autoCreate(@RequestBody String url) {

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
