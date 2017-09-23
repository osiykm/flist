package com.osiykm.flist.services;

import com.osiykm.flist.entities.Book;
import com.osiykm.flist.entities.Category;
import com.osiykm.flist.repositories.CategoryRepository;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.data.repository.CrudRepository;

import static org.junit.Assert.*;
import static org.mockito.Matchers.anyObject;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.when;

/***
 * @author osiykm
 * created 23.09.2017 22:00
 */
public class CategoryServiceTest {
    private CategoryService service;
    @Mock
    private CategoryRepository mockRepository;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        service = new CategoryService(mockRepository);
    }

    @Test
    public void save() throws Exception {
        when(mockRepository.save((Category) anyObject())).then(p -> p.getArguments()[0]);
        System.out.println(service.save("Unbreakable Machine-Doll/機巧少女は傷つかない"));
    }

}