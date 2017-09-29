package com.osiykm.flist.services;

import com.osiykm.flist.entities.Book;
import com.osiykm.flist.entities.Category;
import com.osiykm.flist.enums.BookStatus;
import com.osiykm.flist.repositories.BookRepository;
import com.osiykm.flist.repositories.CategoryRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Comparator;
import java.util.StringJoiner;
import java.util.logging.SimpleFormatter;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

/***
 * @author osiykm
 * created 29.09.2017 20:57
 */
@Service
@Slf4j
public class ListCreatorService {
    private final CategoryRepository categoryRepository;

    private final long HALF_YEAR = 1000L * 60 * 60 * 24 * 30 * 6;

    @Autowired
    public ListCreatorService(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    public String createList() {
        StringJoiner headerList = new StringJoiner("\n", "<ul>", "</ul>");
        StringJoiner bodyList = new StringJoiner("\n");
        StreamSupport.stream(categoryRepository.findAll().spliterator(), false).filter(p -> p.getBooks().size() != 0).sorted(Comparator.comparing(Category::getName)).forEach(p -> {
            headerList.add("<li><a href=\"#" + p.getCode() + "\">" + p.getName() + "</a></li>");
            bodyList.add("<h3 id=\"" + p.getCode() + "\">" + p.getName() + "</h3>\n").add("")
                    .add("<p><a href=\"" + p.getCode() + "_crossovers\">Crossovers</a></p>\n")
                    .add(p.getBooks().stream()
                            .filter(book -> book.getCategories().size() == 1)
                            .map(this::createDescription)
                            .collect(Collectors.joining("\n")))
                    .add("<h4 id=\"" + p.getCode() + "_crossovers\">" + p.getName() + " Crossovers</h4>")
                    .add(p.getBooks().stream()
                            .filter(book -> book.getCategories().size() != 1)
                            .map(this::createDescription)
                            .collect(Collectors.joining("\n")));
        });
        return headerList + "\n" + bodyList;
    }

    private String createDescription(Book book) {
        StringJoiner joiner = new StringJoiner("\n<br>\n", "<p>", "</p>");
        joiner
                .add("<b>Name:</b> <a href=\"" + book.getUrl() + "\">" + book.getName() + "</a>")
                .add("<b>Name:</b> " + getStatus(book))
                .add("<b>Size:</b> " + book.getSize() + " words(" + book.getChapters() + " chapters)")
                .add("<b>Last updated:</b> " + new SimpleDateFormat("dd/mm/yyyy").format(book.getUpdated()))
                .add("<b>Fandoms: </b>" + book.getCategories().stream().map(Category::getName).collect(Collectors.joining(",")))
                .add("<b>Description:</b> " + book.getDescription())
                .add("<b>Comentary:</b> " + book.getCommentary());
        return null;
    }

    private String getStatus(Book book) {
        if (book.getStatus().equals(BookStatus.COMPLETED))
            return "<font color=\"green\">COMPLETED</font>";
        else if (Calendar.getInstance().getTimeInMillis() - book.getUpdated().getTime() > HALF_YEAR) {
            return "<font color=\"red\">DEAD</font>";
        }
        return "<font color=\"blue\">LIVE</font>";
    }
}
