package com.osiykm.flist.repositories;

import com.osiykm.flist.entities.Task;
import com.osiykm.flist.enums.TaskStatus;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

/***
 * @author osiykm
 * created 27.09.2017 21:03
 */
public interface TaskRepository extends CrudRepository<Task, Long> {
    List<Task> findByStatus(TaskStatus status);
}
