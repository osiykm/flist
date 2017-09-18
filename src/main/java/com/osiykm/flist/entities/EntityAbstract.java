package com.osiykm.flist.entities;

import lombok.EqualsAndHashCode;
import org.springframework.hateoas.Identifiable;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

@MappedSuperclass
@EqualsAndHashCode
public abstract class EntityAbstract implements Identifiable<Long> {
    @Id
    @GeneratedValue
    private Long id;

    @Override
    public Long getId() {
        return id;
    }
}
