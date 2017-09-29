package com.osiykm.flist.entities;

import com.osiykm.flist.enums.TaskStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;
import java.util.Date;

/***
 * @author osiykm
 * created 27.09.2017 20:59
 */
@Accessors(chain = true)
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
public class Task {

    @Id
    @GeneratedValue
    private Long id;

    @NotNull
    private String url;

    @Builder.Default
    private Date created = new Date();

    @Builder.Default
    private TaskStatus status = TaskStatus.WAITING;
}
