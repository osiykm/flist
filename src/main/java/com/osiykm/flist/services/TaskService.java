package com.osiykm.flist.services;

import com.osiykm.flist.entities.Task;
import com.osiykm.flist.repositories.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Calendar;
import java.util.HashSet;
import java.util.Set;

/***
 * @author osiykm
 * created 30.09.2017 22:32
 */
@Service
public class TaskService {
    private final TaskRepository taskRepository;

    @Autowired
    public TaskService(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }


    public Set<Task> saveAll(Set<Task> tasks) {
        Set<Task> response = new HashSet<>();
        for (Task task :
                tasks) {
            try {
                response.add(taskRepository.save(task));
            } catch (Exception ignored) {}
        }
        return response;
    }

}
