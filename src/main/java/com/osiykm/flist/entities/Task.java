package com.osiykm.flist.entities;

import com.osiykm.flist.enums.TaskStatus;
import lombok.*;
import lombok.experimental.Accessors;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Date;

/***
 * @author osiykm
 * created 27.09.2017 20:59
 */
@Accessors(chain = true)
@Data
@NoArgsConstructor
@Entity
@Table(name = "tasks")
public class Task {

    @Id
    @GeneratedValue
    private Long id;

    @NotNull
    @Column(unique = true)
    private String url;


    private Date created = new Date();

    @Enumerated(EnumType.STRING)
    private TaskStatus status = TaskStatus.WAITING;

    public Task(String url) {
        this.url = url;
    }
}
