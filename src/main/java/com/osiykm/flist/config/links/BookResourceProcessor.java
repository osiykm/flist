package com.osiykm.flist.config.links;

import com.osiykm.flist.entities.Book;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.support.RepositoryEntityLinks;
import org.springframework.hateoas.Resource;
import org.springframework.hateoas.ResourceProcessor;
import org.springframework.stereotype.Component;

@Component
public class BookResourceProcessor implements ResourceProcessor<Resource<Book>>{
    private final RepositoryEntityLinks links;

    @Autowired
    public BookResourceProcessor(RepositoryEntityLinks links) {
        this.links = links;
    }

    @Override
    public Resource<Book> process(Resource<Book> resource) {
        resource.add(links.linkToSingleResource(resource.getContent()).withRel("update"));
        resource.add(links.linkToSingleResource(resource.getContent()).withRel("delete"));
        return resource;
    }
}
