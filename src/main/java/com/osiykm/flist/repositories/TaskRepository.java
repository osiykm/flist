package com.osiykm.flist.repositories;

import com.osiykm.flist.entities.Task;
import com.osiykm.flist.enums.TaskStatus;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.Repository;

/***
 * @author osiykm
 * created 27.09.2017 21:03
 */
public interface TaskRepository extends Repository<Task, Long> {
    Iterable<Task> findAll();
    Iterable<Task> save(Iterable<Task> tasks);
    Iterable<Task> findByStatus(TaskStatus status);
}
