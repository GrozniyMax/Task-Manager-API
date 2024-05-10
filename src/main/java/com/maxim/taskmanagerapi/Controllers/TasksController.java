package com.maxim.taskmanagerapi.Controllers;


import com.maxim.taskmanagerapi.TaskManagers.TaskManager;
import com.maxim.taskmanagerapi.Tasks.SimpleTask;
import com.maxim.taskmanagerapi.Tasks.Task;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/courses")
public class TasksController {

    @Autowired
    TaskManager taskManager;


    @GetMapping("/{id}")
    public @ResponseBody Task getCourseById(@PathVariable Long id){
        System.out.println("SUIIII");
        return new SimpleTask("SUIIIII");
    }
}
