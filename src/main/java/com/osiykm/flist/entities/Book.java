package com.osiykm.flist.entities;

import com.osiykm.flist.enums.BookStatus;
import lombok.Builder;
import lombok.Data;
import org.springframework.hateoas.Identifiable;

import javax.persistence.*;
import java.util.Date;
import java.util.List;


@Entity
@Data
@Builder
@Table(name = "books")
public class Book implements Identifiable<Long> {
    @Id
    @GeneratedValue
    private Long id;

    @Column(nullable = false)
    private String name;

    @Enumerated
    @Column(nullable = false)
    private BookStatus status;

    @ManyToOne
    @JoinColumn(name = "author_id", nullable = false)
    private Author author;

    @ManyToMany(mappedBy = "books")
    private List<Category> categories;

    @Column(nullable = false)
    @GeneratedValue()
    @Temporal(value = TemporalType.TIMESTAMP)
    private Date created;

    @Column(nullable = false, updatable = false)
    @Temporal(value = TemporalType.TIMESTAMP)
    private Date added;

    @Column(nullable = false)
    @Temporal(value = TemporalType.TIMESTAMP)
    private Date updated;
}