package com.osiykm.flist.services.parser;

import com.osiykm.flist.entities.Author;
import com.osiykm.flist.entities.Book;
import com.osiykm.flist.services.WebDriverComponent;
import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;
import java.util.stream.Collectors;

import static org.junit.Assert.*;

/***
 * @author osiykm
 * created 16.10.2017 21:54
 */
public class SVUrlParserServiceTest {
    private WebDriverComponent webDriverComponent = new WebDriverComponent();
    private  SVUrlParserService parser;
    @Before
    public void load() {
        this.parser = new SVUrlParserService(webDriverComponent);
    }

    @Test
    public void getBook() throws Exception {
        Book book =  parser.getBook("https://forums.sufficientvelocity.com/threads/an-essence-of-silver-and-steel-worm-fate-stay-night-alt-power.39043/page-122#post-9330021");
        System.out.println(book);
    }

    @Test
    public void getAuthor() throws Exception {
        Author author = parser.getAuthor("https://forums.sufficientvelocity.com/threads/an-essence-of-silver-and-steel-worm-fate-stay-night-alt-power.39043/page-122#post-9330021");
        System.out.println(author);
    }

    @Test
    public void tempTest() {
        System.out.println(Arrays.stream("6.9".split("\\.")).collect(Collectors.joining(" , ")));
    }

}