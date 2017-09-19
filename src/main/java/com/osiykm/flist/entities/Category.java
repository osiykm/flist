package com.osiykm.flist.entities;

import lombok.Builder;
import lombok.Data;
import org.springframework.hateoas.Identifiable;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
@Builder
public class Category implements Identifiable<Long> {

    @Id
    @GeneratedValue
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String code;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "book_category",
            joinColumns = @JoinColumn(name = "book_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "categorY_id",
                    referencedColumnName = "id"))
    private List<Book> books;


}
