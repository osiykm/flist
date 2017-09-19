package com.osiykm.flist.entities;

import lombok.*;
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

    @Column(nullable = false)
    @Temporal(value = TemporalType.TIMESTAMP)
    private Date created;

    @Column(nullable = false)
    @Temporal(value = TemporalType.TIMESTAMP)
    private Date added;

    @Column(nullable = false)
    @Temporal(value = TemporalType.TIMESTAMP)
    private Date updated;

    @Override
    public Long getId() {
        return id;
    }
}
