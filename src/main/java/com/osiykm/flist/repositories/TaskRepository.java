package com.osiykm.flist.repositories;

import com.osiykm.flist.entities.Task;
import com.osiykm.flist.enums.TaskStatus;
import org.springframework.data.repository.Repository;

import java.util.List;

/***
 * @author osiykm
 * created 27.09.2017 21:03
 */
public interface TaskRepository extends Repository<Task, Long> {
    List<Task> findAll();
    List<Task> save(Iterable<Task> tasks);
    List<Task> findByStatus(TaskStatus status);
}
