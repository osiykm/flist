package com.osiykm.flist.services;

import com.google.common.collect.Lists;
import com.osiykm.flist.entities.Book;
import com.osiykm.flist.entities.Category;
import com.osiykm.flist.enums.BookStatus;
import com.osiykm.flist.repositories.CategoryRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Comparator;
import java.util.List;
import java.util.StringJoiner;
import java.util.stream.Collectors;

/***
 * @author osiykm
 * created 29.09.2017 20:57
 */
@Service
@Slf4j
public class ListCreatorService {
    private final CategoryRepository categoryRepository;

    private final long YEAR = 1000L * 60 * 60 * 24 * 30 * 12;

    @Autowired
    public ListCreatorService(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    public String createList() {
        StringJoiner headerList = new StringJoiner("\n", "<ul>", "</ul>");
        StringJoiner bodyList = new StringJoiner("\n");
        List<Category> categories = Lists.newArrayList(categoryRepository.findAll());
        categories.stream().filter(p -> p.getBooks().stream().anyMatch(book -> !book.getStatus().equals(BookStatus.UNPUBLISHED))).sorted(Comparator.comparing(Category::getName)).forEach(category -> {
            headerList.add("<li><a href=\"#" + category.getCode() + "\">" + category.getName() + "</a></li>");
            bodyList.add(getCategory(category));
        });
        return headerList + "\n" + bodyList;
    }

    private String createDescription(Book book) {
        StringJoiner joiner = new StringJoiner("\n<br>\n", "<p>", "</p>");
        joiner
                .add("<b>Название:</b> <a href=\"" + book.getUrl() + "\">" + book.getName() + "</a>")
                .add("<b>Автор:</b> <a href=\"" + book.getAuthor().getUrl() + "\">" + book.getAuthor().getName() + "</a>")
                .add("<b>Фендомы: </b>" + book.getCategories().stream().map(p -> "<a href=\"#"+p.getCode()+"\">"+p.getName()+"</a>").collect(Collectors.joining(", ")))
                .add("<b>Статус:</b> " + getStatus(book))
                .add("<b>Размер:</b> " + new DecimalFormat("#,##0").format(book.getSize()) + " слов (" + book.getChapters() + " глав)")
                .add("<b>Последнее обновление:</b> " + new SimpleDateFormat("dd/MM/yyyy").format(book.getUpdated()))
                .add("<b>Описание:</b> " + book.getDescription());
        if (book.getCommentary().length() > 0)
            joiner.add("<b>Коментарий:</b> " + book.getCommentary());
        return joiner.toString();
    }

    private String getStatus(Book book) {
        if (book.getStatus().equals(BookStatus.COMPLETED))
            return "<font color=\"green\">Завершен</font>";
        else if (Calendar.getInstance().getTimeInMillis() - book.getUpdated().getTime() > YEAR) {
            return "<font color=\"red\">Заморожен</font>";
        }
        return "<font color=\"blue\">Пишется</font>";
    }

    private String getCategory(Category category) {
        StringJoiner joiner = new StringJoiner("\n");
        joiner.add("<h3 id=\"" + category.getCode() + "\">" + category.getName() + "</h3>\n").add("");
        if (category.getBooks().stream().anyMatch(book -> book.getCategories().size() != 1) && category.getBooks().stream().anyMatch(book -> book.getCategories().size() == 1))
            joiner.add("<p><a href=\"#" +
                    "" + category.getCode() + "_crossovers\">Перейти к кросоверам</a></p>\n");
        joiner.add(category.getBooks().stream()
                .filter(book -> book.getCategories().size() == 1)
                .map(this::createDescription)
                .collect(Collectors.joining("\n")));
        if (category.getBooks().stream().anyMatch(book -> book.getCategories().size() != 1))
            joiner.add("<h4 id=\"" + category.getCode() + "_crossovers\">" + category.getName() + " Кросоверы</h4>")
                    .add(category.getBooks().stream()
                            .filter(book -> book.getCategories().size() != 1)
                            .map(this::createDescription)
                            .collect(Collectors.joining("\n")));
        return joiner.toString();
    }
}
