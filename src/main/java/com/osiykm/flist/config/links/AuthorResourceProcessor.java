package com.osiykm.flist.config.links;

import com.osiykm.flist.entities.Author;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.support.RepositoryEntityLinks;
import org.springframework.hateoas.Resource;
import org.springframework.hateoas.ResourceProcessor;
import org.springframework.stereotype.Component;

@Component
public class AuthorResourceProcessor implements ResourceProcessor<Resource<Author>> {
    private final RepositoryEntityLinks links;

    @Autowired
    public AuthorResourceProcessor(RepositoryEntityLinks links) {
        this.links = links;
    }

    @Override
    public Resource<Author> process(Resource<Author> resource) {
        resource.add(links.linkToSingleResource(resource.getContent()).withRel("update"));
        resource.add(links.linkToSingleResource(resource.getContent()).withRel("delete"));
        return resource;
    }
    }
