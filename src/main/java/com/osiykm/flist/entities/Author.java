package com.osiykm.flist.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Author extends EntityAbstract {

    private String name;

    private String url;

    @OneToMany(mappedBy = "author", cascade = CascadeType.ALL)
    private List<Book> books;

    @Temporal(value = TemporalType.TIMESTAMP)
    private Date created;

    @Temporal(value = TemporalType.TIMESTAMP)
    private Date added;

    @Temporal(value = TemporalType.TIMESTAMP)
    private Date updated;
}
