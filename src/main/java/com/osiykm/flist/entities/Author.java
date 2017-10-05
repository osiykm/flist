package com.osiykm.flist.entities;

import lombok.*;
import org.springframework.hateoas.Identifiable;

import javax.persistence.*;
import java.util.List;


@Entity
@Data
@Table(name = "authors")
@EqualsAndHashCode(exclude = "books")
@ToString(exclude = "books")
@NoArgsConstructor
public class Author implements Identifiable<Long> {
    @Id
    @GeneratedValue
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false, unique = true)
    private String url;


    @OneToMany(mappedBy = "author", fetch = FetchType.EAGER)
    private List<Book> books;

    @Builder
    public Author(String name, String url) {
        this.name = name;
        this.url = url;
    }
}
