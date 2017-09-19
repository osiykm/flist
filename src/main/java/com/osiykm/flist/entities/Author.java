package com.osiykm.flist.entities;

import lombok.Builder;
import lombok.Data;
import org.springframework.hateoas.Identifiable;

import javax.persistence.*;
import java.util.Date;
import java.util.List;


@Entity
@Data
@Table(name = "authors")
@Builder
public class Author implements Identifiable<Long> {
    @Id
    @GeneratedValue
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String url;


    @OneToMany
    @Column(nullable = false)
    private List<Book> books;
}
