package com.osiykm.flist.entities;

import com.osiykm.flist.enums.BookStatus;
import lombok.Builder;
import lombok.Data;
import org.springframework.hateoas.Identifiable;

import javax.persistence.*;
import java.util.List;


@Entity
@Data
@Builder
public class Book implements Identifiable<Long> {
    @Id
    @GeneratedValue
    private Long id;

    private String name;

    @Enumerated
    private BookStatus status;

    @ManyToOne
    @JoinColumn(name = "author_id", nullable = false)
    private Author author;

    @ManyToMany(mappedBy = "books")
    private List<Category> categories;
}