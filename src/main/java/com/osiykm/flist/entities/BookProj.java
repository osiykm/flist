package com.osiykm.flist.entities;

import com.osiykm.flist.enums.BookStatus;
import org.springframework.data.rest.core.config.Projection;

import java.util.Date;
import java.util.Set;

/***
 * @author osiykm
 * created 05.10.2017 21:17
 */
@Projection(name = "fullBook",types = {Book.class})
public interface BookProj {
    String getName();
    String getDescription();
    String getCommentary();
    BookStatus getStatus();
    Author getAuthor();
    Set<Category> getCategories();
    String getUrl();
    Date getAdded();
    Date getUpdated();
    Date getCreated();
    Integer getSize();
    Integer getChapters();
}
