package com.maxim.taskmanagerapi.Security;

import com.maxim.taskmanagerapi.DataBaseLogic.Repositories.TaskRepo;
import com.maxim.taskmanagerapi.DataBaseLogic.Repositories.UserRepo;
import com.maxim.taskmanagerapi.Entities.Tasks.DTOs.TaskDTO;
import com.maxim.taskmanagerapi.Entities.Users.UserAdapter;
import com.maxim.taskmanagerapi.Entities.Users.UserDTO;
import com.maxim.taskmanagerapi.Security.Encryption.Tokenizer;
import com.maxim.taskmanagerapi.TaskManagers.MultiClients.TaskManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.support.Repositories;
import org.springframework.expression.AccessException;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AccessChecher{

    @Autowired
    private Tokenizer tokenizer;

    @Autowired
    private TaskManager taskManager;

    @Autowired
    private UserAdapter adapter;

    @Autowired
    private UserRepo userRepo;

    public TaskDTO getTaskByID(Long id, String tokenS) throws AccessException {
        checkAccess(tokenS,id);
        return taskManager.getTaskDTOByID(id);
    }

    public void add(TaskDTO taskDTO,String tokenS) throws AccessException {
        checkAccess(tokenS,taskDTO.getId());
        taskManager.add(taskDTO,tokenizer.detokenizeToDTO(tokenS));
    }

    public void update(Long id, TaskDTO taskDTO,String tokenS) throws AccessException {
        checkAccess(tokenS,id);
        taskManager.update(id, taskDTO, tokenizer.detokenizeToDTO(tokenS));
    }


    public List<TaskDTO> getTasks(String tokenS) throws AccessException {
        Token token = tokenizer.detokenize(tokenS);
        chechIfTokenExpired(token);
        return taskManager.getTasks(
                adapter.toUserDAO(adapter.toUserDTO(token))
        );
    }

    private void chechIfTokenExpired(Token token) throws AccessException {
        if (token.isExpired()) {
            throw new AccessException("Token expired");
        }
    }

    private void checkAccess(Token token, Long taskId) throws AccessException {
        chechIfTokenExpired(token);
        var task = taskManager.getTaskDAOByID(taskId);
        var user = userRepo.findById(token.id()).orElseThrow(()->new AccessException("User not found"));
        if (!(task.getOwnerId().equals(user))) throw new AccessException("Task owner mismatch");
    }

    private void checkAccess(String tokenS, Long taskId) throws AccessException {
        Token token = tokenizer.detokenize(tokenS);
        chechIfTokenExpired(token);
        var task = taskManager.getTaskDAOByID(taskId);
        var user = userRepo.findById(token.id()).orElseThrow(()->new AccessException("User not found"));
        if (!(task.getOwnerId().equals(user))) throw new AccessException("Task owner mismatch");
    }
}
