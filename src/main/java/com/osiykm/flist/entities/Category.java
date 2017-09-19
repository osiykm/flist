package com.osiykm.flist.entities;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.List;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@RequiredArgsConstructor()
@Getter
@Setter
public class Category extends EntityAbstract {
    @NonNull
    @NotNull
    private String name;

    @NonNull
    @NotNull
    private String code;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "book_category",
            joinColumns = @JoinColumn(name = "book_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "categorY_id",
                    referencedColumnName = "id"))
    private List<Book> books;


}
