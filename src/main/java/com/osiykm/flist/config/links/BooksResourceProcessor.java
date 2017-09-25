package com.osiykm.flist.config.links;

import com.osiykm.flist.controllers.BookController;
import com.osiykm.flist.controllers.BookUrlRequest;
import com.osiykm.flist.entities.Book;
import org.springframework.data.rest.webmvc.PersistentEntityResourceAssembler;
import org.springframework.hateoas.Resource;
import org.springframework.hateoas.ResourceProcessor;
import org.springframework.hateoas.Resources;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;
/***
 * @author osiykm
 * created 25.09.2017 14:05
 */
@Component
public class BooksResourceProcessor implements ResourceProcessor<Resources<Resource<Book>>> {
        @Override
    public Resources<Resource<Book>> process(Resources<Resource<Book>> resource) {
        resource.add(linkTo(methodOn(BookController.class).parseBook(new BookUrlRequest(new Book().getUrl()), null)).withRel("magic"));
        return resource;
    }
}
