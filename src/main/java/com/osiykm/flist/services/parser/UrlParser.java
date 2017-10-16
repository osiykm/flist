package com.osiykm.flist.services.parser;

import com.osiykm.flist.entities.Author;
import com.osiykm.flist.entities.Book;
import com.osiykm.flist.entities.Category;

import java.util.Set;

/***
 * @author osiykm
 * created 16.10.2017 13:42
 */
public interface UrlParser {
    Book getBook(String url);

    Author getAuthor(String url);

    Set<Category> getCategories(String url);

    void unlock();
}
