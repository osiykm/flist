package com.osiykm.flist.controllers;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.osiykm.flist.entities.Task;
import com.osiykm.flist.services.TaskService;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Set;
import java.util.stream.Collectors;

/***
 * @author osiykm
 * created 30.09.2017 22:30
 */

@RestController
public class TaskController {
    private final TaskService taskService;

    @Autowired
    public TaskController(TaskService taskService) {
        this.taskService = taskService;
    }

    @RequestMapping(value = "tasks/all", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<Void> parser(@RequestBody TaskRequest request) {
        taskService.saveAll(request.getUrls().stream().map(Task::new).collect(Collectors.toSet()));
        return ResponseEntity.ok().build();
    }
}

@NoArgsConstructor
@Getter
@AllArgsConstructor
@JsonIgnoreProperties
class TaskRequest {
    private Set<String> urls;
}

