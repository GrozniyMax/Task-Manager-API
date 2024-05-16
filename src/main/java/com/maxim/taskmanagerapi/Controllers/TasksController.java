package com.maxim.taskmanagerapi.Controllers;


import com.maxim.taskmanagerapi.Entities.Users.UserDAO;
import com.maxim.taskmanagerapi.DataBaseLogic.Repositories.UserRepo;
import com.maxim.taskmanagerapi.TaskManagers.OneClient.TaskManager;
import com.maxim.taskmanagerapi.Entities.Tasks.DTOs.ComplexTaskDTO;
import com.maxim.taskmanagerapi.Entities.Tasks.DTOs.TaskDTO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/tasks")
public class TasksController {

    @Autowired
    TaskManager taskManager;

    @Autowired
    UserRepo userRepository;

    @GetMapping("/")
    public List<TaskDTO> getTasks() {
        return taskManager.getTasks();
    }

    @GetMapping("/{id}")
    public @ResponseBody TaskDTO getCourseById(@PathVariable Long id){
        System.out.println("SUIIII");
        return taskManager.getTaskByID(id);
    }

    @PostMapping("/")
    public void addTask(@RequestBody TaskDTO taskDTO){
        taskManager.add(taskDTO);
    }

    @PostMapping("/{id}")
    public void updateTask(@PathVariable Long id, @RequestBody TaskDTO taskDTO){
        taskManager.update(id, taskDTO);
    }

    @PostMapping("/{id}/subtasks")
    public void addSubTask(@PathVariable Long id, @RequestBody TaskDTO taskDTO){
         ((ComplexTaskDTO) taskManager.getTaskByID(id)).addSubTask(taskDTO);
    }

    @GetMapping("/{id}/subtasks")
    public Map<Long,List<TaskDTO>> getSubTasks(@PathVariable Long id){
        return ((ComplexTaskDTO) taskManager.getTaskByID(id)).getSubTasksByLayer();
    }

    @GetMapping("/user")
    public @ResponseBody UserDAO gg(){
        Optional<UserDAO> optional =userRepository.findById(1L);
        return optional.get();

    }

    @ExceptionHandler(ClassCastException.class)
    public @ResponseBody String handleClassCastException(ClassCastException e){
        return e.getMessage();
    }

}
