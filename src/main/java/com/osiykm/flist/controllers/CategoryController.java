package com.osiykm.flist.controllers;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.osiykm.flist.entities.Category;
import com.osiykm.flist.services.CategoryService;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.PersistentEntityResource;
import org.springframework.data.rest.webmvc.PersistentEntityResourceAssembler;
import org.springframework.data.rest.webmvc.RepositoryRestController;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.validation.constraints.NotNull;

@RepositoryRestController
public class CategoryController {

    private final CategoryService categoryService;

    @Autowired
    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @RequestMapping(value= "/categories", method = RequestMethod.POST)
    @ResponseBody

    public PersistentEntityResource post(@RequestBody CategoryRequest request, PersistentEntityResourceAssembler as) {
        return as.toFullResource(categoryService.save(request.getName()));
    }

}
@NoArgsConstructor
@Getter
@AllArgsConstructor
@JsonIgnoreProperties
class   CategoryRequest {
    @NotNull
    private String name;
}

