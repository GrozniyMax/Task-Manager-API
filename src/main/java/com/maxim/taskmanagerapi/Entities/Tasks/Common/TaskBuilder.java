package com.maxim.taskmanagerapi.Entities.Tasks.Common;

import com.maxim.taskmanagerapi.Entities.Tasks.DTOs.TaskDTO;
import jakarta.annotation.Nullable;

import java.time.LocalDateTime;
import java.util.List;

/**
 * Interface for abstract task builder
 * <h3>ANY Task..Builder also must realize methond</h3>
 * <ul>.... build()</ul>
 * <ul>.... setAttributes</ul>
 */
public interface TaskBuilder {

    public TaskBuilder setID(Long id);

    public TaskBuilder setLabel(String label);

    public TaskBuilder setDescription(String description);

    public TaskBuilder setStatus(Status status);


    public TaskBuilder setSubtask(@Nullable List<TaskDTO> subtask);

    public TaskBuilder setStartTime(@Nullable LocalDateTime startTime);

    public TaskBuilder setDeadline(@Nullable LocalDateTime deadline);

    // Besides method build but returning type depends on builder

}
