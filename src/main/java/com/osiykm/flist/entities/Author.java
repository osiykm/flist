package com.osiykm.flist.entities;

import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.springframework.hateoas.Identifiable;

import javax.persistence.*;
import java.util.Date;
import java.util.List;


@Entity
@Data
@Table(name = "authors")
@EqualsAndHashCode(exclude = "books")
@NoArgsConstructor
public class Author implements Identifiable<Long> {
    @Id
    @GeneratedValue
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String url;


    @OneToMany(mappedBy = "author")
    private List<Book> books;

    @Builder
    public Author(String name, String url) {
        this.name = name;
        this.url = url;
    }
}
