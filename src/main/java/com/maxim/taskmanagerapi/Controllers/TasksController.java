package com.maxim.taskmanagerapi.Controllers;


import com.maxim.taskmanagerapi.TaskLogic.TaskManagers.TaskManager;
import com.maxim.taskmanagerapi.TaskLogic.Tasks.ComplexTask;
import com.maxim.taskmanagerapi.TaskLogic.Tasks.Task;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/tasks")
public class TasksController {

    @Autowired
    TaskManager taskManager;

    @GetMapping("/")
    public List<Task> getTasks() {
        return taskManager.getTasks();
    }

    @GetMapping("/{id}")
    public @ResponseBody Task getCourseById(@PathVariable Long id){
        System.out.println("SUIIII");
        return taskManager.getTaskByID(id);
    }

    @PostMapping("/")
    public void addTask(@RequestBody Task task){
        taskManager.add(task);
    }

    @PostMapping("/{id}")
    public void updateTask(@PathVariable Long id, @RequestBody Task task){
        taskManager.update(id,task);
    }

    @PostMapping("/{id}/subtasks")
    public void addSubTask(@PathVariable Long id, @RequestBody Task task){
         ((ComplexTask) taskManager.getTaskByID(id)).addSubTask(task);
    }

    @GetMapping("/{id}/subtasks")
    public Map<Long,List<Task>> getSubTasks(@PathVariable Long id){
        return ((ComplexTask) taskManager.getTaskByID(id)).getSubTasksByLayer();
    }

    @ExceptionHandler(ClassCastException.class)
    public @ResponseBody String handleClassCastException(ClassCastException e){
        return e.getMessage();
    }

}
