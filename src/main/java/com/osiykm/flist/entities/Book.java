package com.osiykm.flist.entities;

import com.osiykm.flist.enums.BookStatus;
import lombok.Builder;
import lombok.Data;
import lombok.Singular;
import org.springframework.hateoas.Identifiable;

import javax.persistence.*;
import java.util.Date;
import java.util.List;


@Entity
@Data
@Table(name = "books")
@Builder
public class Book implements Identifiable<Long> {



    @Id
    @GeneratedValue
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String description;

    @Column(nullable = false)
    @Builder.Default
    private String commentary = "";

    @Enumerated
    @Column(nullable = false)
    @Builder.Default
    private BookStatus status = BookStatus.UNPUBLISHED;

    @ManyToOne
    @JoinColumn(name = "author_id", nullable = false)
    private Author author;

    @ManyToMany(mappedBy = "books")
    private List<Category> categories;


    @Column(nullable = false, updatable = false )
    @Temporal(value = TemporalType.TIMESTAMP)
    @Builder.Default
    private Date added = new Date();


    @Temporal(value = TemporalType.TIMESTAMP)
    private Date updated;

    @Column(nullable = false)
    @Temporal(value = TemporalType.TIMESTAMP)
    private Date created;


//    public  Book(String name, String description, BookStatus status, Author author, List<Category> categories, Date updated, Date created, String commentary) {
//        this.name = name;
//        this.description = description;
//        this.status = status;
//        this.author = author;
//        this.categories = categories;
//        this.updated = updated;
//        this.created = created;
//        this.commentary = commentary;
//    }
}