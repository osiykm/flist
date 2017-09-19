package com.osiykm.flist.config;

import com.osiykm.flist.entities.Category;
import com.osiykm.flist.repositories.CategoryRepository;
import org.springframework.data.rest.core.config.RepositoryRestConfiguration;
import org.springframework.data.rest.webmvc.config.RepositoryRestConfigurerAdapter;
import org.springframework.stereotype.Component;

@Component
public class RestUrlCustomize extends RepositoryRestConfigurerAdapter{

    @Override
    public void configureRepositoryRestConfiguration(RepositoryRestConfiguration config) {
        config.withEntityLookup().//
                forRepository(CategoryRepository.class).withIdMapping(Category::getCode).withLookup(CategoryRepository::findByCode);
    }
}
