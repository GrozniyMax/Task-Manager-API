package com.maxim.taskmanagerapi.Controllers;


import com.maxim.taskmanagerapi.Entities.Users.UserDAO;
import com.maxim.taskmanagerapi.DataBaseLogic.Repositories.UserRepo;
import com.maxim.taskmanagerapi.Security.AccessChecher;
import com.maxim.taskmanagerapi.Entities.Tasks.DTOs.ComplexTaskDTO;
import com.maxim.taskmanagerapi.Entities.Tasks.DTOs.TaskDTO;

import org.apache.coyote.BadRequestException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.expression.AccessException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/tasks")
public class TasksController {

    @Autowired
    AccessChecher taskManager;

    @Autowired
    UserRepo userRepository;

    @GetMapping("/")
    public List<TaskDTO> getTasks(@RequestParam String token) throws AccessException {
        return taskManager.getTasks(token);
    }

    @GetMapping("/{id}")
    public @ResponseBody TaskDTO getCourseById(@PathVariable Long id, @RequestParam String token) throws AccessException {
        System.out.println("SUIIII");
        return taskManager.getTaskByID(id,token);
    }

    @PostMapping("/")
    public void addTask(@RequestBody TaskDTO taskDTO,
                        @RequestParam String token) throws AccessException {
        taskManager.add(taskDTO,token);
    }

    @PostMapping("/{id}")
    public void updateTask(@PathVariable Long id, @RequestBody TaskDTO taskDTO,
                           @RequestParam String token) throws AccessException {
        taskManager.update(id, taskDTO,token);
    }

    @PostMapping("/{id}/subtasks")
    public void addSubTask(@PathVariable Long id, @RequestBody TaskDTO taskDTO,
                           @RequestParam String token) throws AccessException {
         ((ComplexTaskDTO) taskManager.getTaskByID(id,token)).addSubTask(taskDTO);
    }

    @GetMapping("/{id}/subtasks")
    public Map<Long,List<TaskDTO>> getSubTasks(@PathVariable Long id,
                                               @RequestParam String token) throws AccessException {
        return ((ComplexTaskDTO) taskManager.getTaskByID(id,token)).getSubTasksByLayer();
    }

    @GetMapping("/user")
    public @ResponseBody UserDAO gg(){
        Optional<UserDAO> optional =userRepository.findById(1L);
        return optional.get();
    }

    @ResponseStatus(value = HttpStatus.UNAUTHORIZED, reason = "Wrong Token")
    @ExceptionHandler(AccessException.class)
    public void handle(AccessException e) {
        // do nothing
    }

    @ExceptionHandler(ClassCastException.class)
    public @ResponseBody String handleClassCastException(ClassCastException e){
        return e.getMessage();
    }

}
