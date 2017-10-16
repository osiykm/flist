package com.osiykm.flist.services.parser;

import com.osiykm.flist.entities.Author;
import com.osiykm.flist.entities.Book;
import com.osiykm.flist.entities.Category;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

/***
 * @author osiykm
 * created 16.10.2017 13:45
 */
@Slf4j
@Service
public class SBUrlParserService implements UrlParser {

    @Override
    public Book getBook(String url) {
        return null;
    }

    @Override
    public Author getAuthor(String url) {
        return null;
    }

    @Override
    public void unlock() {

    }

    @Override
    public Set<Category> getCategories(String url) {
        return new HashSet<>();
    }
}
