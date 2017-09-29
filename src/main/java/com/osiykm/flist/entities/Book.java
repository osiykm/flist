package com.osiykm.flist.entities;

import com.osiykm.flist.enums.BookStatus;
import lombok.*;
import lombok.experimental.Accessors;
import org.springframework.hateoas.Identifiable;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;


@Accessors(chain = true)
@Entity
@Data
@Table(name = "books")
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(exclude = "categories")
@Builder
public class Book implements Identifiable<Long> {


    @Id
    @GeneratedValue
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false, length = 1024)
    private String description;

    @Column(nullable = false, length = 1024)
    @Builder.Default
    private String commentary = "";

    @Enumerated
    @Column(nullable = false)
    @Builder.Default
    private BookStatus status = BookStatus.UNPUBLISHED;

    @ManyToOne
    @JoinColumn(name = "author_id", nullable = false)
    private Author author;

    @ManyToMany(cascade = {CascadeType.DETACH}, fetch = FetchType.EAGER)
    @Builder.Default
    private Set<Category> categories = new HashSet<>();

    @Column(nullable = false, unique = true)
    @NotNull
    private String url;

    @Column(nullable = false, updatable = false)
    @Temporal(value = TemporalType.TIMESTAMP)
    @Builder.Default
    private Date added = new Date();


    @Temporal(value = TemporalType.TIMESTAMP)
    private Date updated;

    @Column(nullable = false)
    @Temporal(value = TemporalType.TIMESTAMP)
    private Date created;

    @Column(nullable = false)
    private Integer size;

    @Column(nullable = false)
    private Integer chapters;
}