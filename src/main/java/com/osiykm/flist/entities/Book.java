package com.osiykm.flist.entities;

import com.osiykm.flist.enums.BookStatus;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

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
