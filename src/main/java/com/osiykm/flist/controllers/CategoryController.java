package com.osiykm.flist.controllers;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.osiykm.flist.services.CategoryService;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.PersistentEntityResource;
import org.springframework.data.rest.webmvc.PersistentEntityResourceAssembler;
import org.springframework.data.rest.webmvc.RepositoryRestController;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
        return as.toFullResource(categoryService.parseCategory(request.getName()));
    }

    @RequestMapping(value = "/categories/{code}", method = RequestMethod.DELETE)
    public ResponseEntity<Void> delete(@PathVariable("code") String code) {
        categoryService.deleteByCode(code);
        return ResponseEntity.ok().build();
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

