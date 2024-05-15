package com.maxim.taskmanagerapi.DataBaseLogic.TasksLogic;

import com.maxim.taskmanagerapi.DataBaseLogic.UserTableLogic.UserDAO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TaskRepo extends JpaRepository<TaskDAO, Long> {

    public TaskDAO findById(long id);

    public List<TaskDAO> findByOwnerId(Long id);

    public default List<TaskDAO> findByOwnerId(UserDAO owner){
        return findByOwnerId(owner.getId());
    }
}
