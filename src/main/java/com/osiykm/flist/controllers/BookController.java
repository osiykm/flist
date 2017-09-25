package com.osiykm.flist.controllers;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.osiykm.flist.entities.Book;
import com.osiykm.flist.services.BookManageService;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.PersistentEntityResource;
import org.springframework.data.rest.webmvc.PersistentEntityResourceAssembler;
import org.springframework.data.rest.webmvc.RepositoryLinksResource;
import org.springframework.data.rest.webmvc.RepositoryRestController;
import org.springframework.hateoas.ExposesResourceFor;
import org.springframework.hateoas.ResourceProcessor;
import org.springframework.hateoas.mvc.ControllerLinkBuilder;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.validation.constraints.NotNull;

/***
 * @author osiykm
 * created 24.09.2017 23:20
 */
@RepositoryRestController
@Slf4j
public class BookController{

    private final BookManageService bookManageService;

    @Autowired
    public BookController(BookManageService bookManageService) {
        this.bookManageService = bookManageService;
    }

    @RequestMapping(value = "/books", method = RequestMethod.POST)
    @ResponseBody
    public PersistentEntityResource parseBook(@RequestBody BookUrlRequest request, PersistentEntityResourceAssembler as) {
        log.info("send url:"+request.getUrl());
        return as.toFullResource(bookManageService.parseBook(request.getUrl()));
    }


}

