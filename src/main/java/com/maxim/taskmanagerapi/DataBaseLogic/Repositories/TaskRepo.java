package com.maxim.taskmanagerapi.DataBaseLogic.Repositories;

import com.maxim.taskmanagerapi.Entities.Users.UserDAO;
import com.maxim.taskmanagerapi.Entities.Tasks.DAOs.TaskDAO;
import com.maxim.taskmanagerapi.Entities.TaskAdapter;
import com.maxim.taskmanagerapi.Entities.Tasks.DTOs.TaskDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TaskRepo extends JpaRepository<TaskDAO, Long> {

    public TaskDAO findById(long id);

    public List<TaskDAO> findByOwnerId(UserDAO owner);

    public default List<TaskDTO> loadTasksByOwner(UserDAO owner){
        return findByOwnerId(owner).stream().map(TaskAdapter::toDTO).toList();
    }

}
