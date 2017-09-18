package com.osiykm.flist.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.osiykm.flist.enums.BookStatus;
import lombok.*;

import javax.persistence.*;
import java.util.List;


@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Book extends EntityAbstract {
    private String name;

    @Enumerated(EnumType.STRING)
    private BookStatus status;

    @ManyToOne
    @JoinColumn(name = "author_id")
    private Author author;

    @ManyToMany(mappedBy = "books")
    private List<Category> categories;
}
