package com.maxim.taskmanagerapi.Entities.Tasks.DTOs;

import com.maxim.taskmanagerapi.Entities.Attributes.AttributeDAO;
import com.maxim.taskmanagerapi.Entities.Tasks.Common.Status;
import com.maxim.taskmanagerapi.Entities.Tasks.Common.TaskBuilder;
import jakarta.annotation.Nullable;
import org.hibernate.resource.beans.container.internal.NotYetReadyException;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

public class TaskDTOBuilder implements TaskBuilder {

    private TaskDTO taskDTO = new SimpleTaskDTO("default");

    private Boolean labelSet = false;
    private Boolean idSet = false;

    public TaskDTOBuilder() {
    }

    public TaskDTOBuilder setID(Long id) {
        this.idSet = true;
        taskDTO.setId(id);
        return this;
    }

    public TaskDTOBuilder setLabel(String label) {
        taskDTO.setLabel(label);
        labelSet = true;
        return this;
    }

    public TaskDTOBuilder setDescription(String description) {
        taskDTO.setDescription(description);
        return this;
    }

    public TaskDTOBuilder setStatus(Status status) {
        taskDTO.setStatus(status);
        return this;
    }


    public TaskDTOBuilder setSubtask(@Nullable List<TaskDTO>  subtask) {
        if (subtask != null||subtask.size() ==0) return this;
        ComplexTaskDTO newTask = (ComplexTaskDTO) taskDTO;
        subtask.forEach(newTask::addSubTask);
        taskDTO = newTask;
        return this;
    }

    public TaskDTOBuilder setStartTime(@Nullable LocalDateTime startTime) {
        taskDTO.setStartTime(startTime);
        return this;
    }

    public TaskDTOBuilder setDeadline(@Nullable LocalDateTime deadline) {
        taskDTO.setDeadline(deadline);
        return this;
    }

    public TaskDTOBuilder setAttributes(@Nullable List<AttributeDAO> attributes) {
        taskDTO.setAttributes(
                attributes.stream().collect(Collectors.toMap(
                        AttributeDAO::getKey,
                        AttributeDAO::getValue
                ))
        );
        return this;
    }

    public TaskDTO build() {
        if (!(idSet&&labelSet)) throw new NotYetReadyException(null);
        return taskDTO;
    }
}
