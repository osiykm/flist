package com.osiykm.flist.services.parser;

import com.osiykm.flist.entities.Author;
import com.osiykm.flist.entities.Book;
import com.osiykm.flist.services.WebDriverComponent;
import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;
import java.util.stream.Collectors;

/***
 * @author osiykm
 * created 16.10.2017 14:03
 */
public class SBUrlParserServiceTest {
    private WebDriverComponent  webDriverComponent = new WebDriverComponent();
    private  SBUrlParserService parser;
    @Before
    public void load() {
        this.parser = new SBUrlParserService(webDriverComponent);
    }

    @Test
    public void getBook() throws Exception {
        Book book =  parser.getBook("https://forums.spacebattles.com/threads/the-sages-disciple-fate-zero-si.542965/threadmarks?category_id=1");
        System.out.println(book);
    }

    @Test
    public void getAuthor() throws Exception {
        Author author = parser.getAuthor("https://forums.spacebattles.com/threads/the-sages-disciple-fate-zero-si.542965/threadmarks?category_id=1");
        System.out.println(author);
    }

    @Test
    public void tempTest() {
        System.out.println(Arrays.stream("6.9".split("\\.")).collect(Collectors.joining(" , ")));
    }
}