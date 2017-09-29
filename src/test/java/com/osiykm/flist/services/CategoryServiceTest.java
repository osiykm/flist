package com.osiykm.flist.services;

import com.osiykm.flist.entities.Category;
import com.osiykm.flist.repositories.CategoryRepository;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.sql.Timestamp;
import java.util.Date;
import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.mockito.Matchers.anyObject;
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
        service = new CategoryService(mockRepository, null);
    }

    @Test
    public void save() throws Exception {
        when(mockRepository.save((Category) anyObject())).then(p -> p.getArguments()[0]);
        assertEquals("unbr-eakable_machine-doll", service.parseCategory("_--_Unbr/eakable Machine-Doll/////機巧少女は傷つかない").getCode());

    }

    @Test
    public void testTemp() {

    }

}