package com.maxim.taskmanagerapi.Entities.Tasks.DAOs;

import com.maxim.taskmanagerapi.Entities.Attributes.AttributeDAO;
import com.maxim.taskmanagerapi.Entities.Tasks.Common.Status;
import com.maxim.taskmanagerapi.Entities.Tasks.Common.TaskBuilder;
import com.maxim.taskmanagerapi.Entities.Tasks.DTOs.TaskDTO;
import jakarta.annotation.Nullable;
import org.hibernate.resource.beans.container.internal.NotYetReadyException;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

public class TaskDAOBuilder implements TaskBuilder {
    private Boolean labelSet = false;
    TaskDAO taskDAO = new TaskDAO();

    public TaskDAOBuilder() {
    }

    @Override
    public TaskDAOBuilder setID(Long id) {
        //do nothing because id generates by hibernate
        return this;
    }

    @Override
    public TaskDAOBuilder setLabel(String label) {
        labelSet = true;
        return this;
    }

    @Override
    public TaskDAOBuilder setDescription(String description) {
        taskDAO.setDescription(description);
        return this;
    }

    @Override
    public TaskDAOBuilder setStatus(Status status) {
        taskDAO.setStatus(status);
        return this;
    }

    @Override
    public TaskDAOBuilder setSubtask(@Nullable List<TaskDTO> subtask){
        //Do nothing because no fielf subtasks
        return this;
    }

    @Override
    public TaskDAOBuilder setStartTime(@Nullable LocalDateTime startTime) {
        taskDAO.setStart(startTime);
        return this;
    }

    @Override
    public TaskDAOBuilder setDeadline(@Nullable LocalDateTime deadline) {
        taskDAO.setDeadline(deadline);
        return this;
    }

    public TaskDAOBuilder setAttributes(@Nullable Map<String, String> attributes) {
        taskDAO.setAttributes(
                attributes.entrySet().stream()
                        .map((entry)->new AttributeDAO(entry.getKey(),entry.getValue(),taskDAO))
                        .toList()
        );
        return this;
    }


    public TaskDAO build() {
        if (!labelSet) throw new NotYetReadyException(null);
        return taskDAO;
    }
}
