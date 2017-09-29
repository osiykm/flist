package com.osiykm.flist.controllers;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.osiykm.flist.entities.Task;
import com.osiykm.flist.repositories.TaskRepository;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.PersistentEntityResource;
import org.springframework.data.rest.webmvc.PersistentEntityResourceAssembler;
import org.springframework.data.rest.webmvc.RepositoryRestController;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;
import java.util.stream.Collectors;

/***
 * @author osiykm
 * created 29.09.2017 20:17
 */
@RepositoryRestController
public class TaskController {

    private final TaskRepository taskRepository;

    @Autowired
    public TaskController(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }


    @RequestMapping(value= "/tasks", method = RequestMethod.POST)
    @ResponseBody
    public PersistentEntityResource post(@RequestBody TasksRequest request, PersistentEntityResourceAssembler as) {
        return as.toFullResource(taskRepository.save(request.getUrls().stream().map(Task::new).collect(Collectors.toSet())) );
    }
}

@NoArgsConstructor
@Getter
@AllArgsConstructor
@JsonIgnoreProperties
class TasksRequest {
    private List<String> urls;
}