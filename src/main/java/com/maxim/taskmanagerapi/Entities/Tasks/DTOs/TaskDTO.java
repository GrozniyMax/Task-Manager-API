package com.maxim.taskmanagerapi.Entities.Tasks.DTOs;

import com.maxim.taskmanagerapi.Entities.Tasks.Common.Status;
import org.springframework.lang.Nullable;

import java.time.LocalDateTime;
import java.util.Map;

public interface TaskDTO {

    public Long getId();

    public String getLabel();

    public String getDescription();

    public Status getStatus();

    public void setStatus(Status status);

    public void setLabel(String label);

    public void setDescription(String description);

    public LocalDateTime getStartTime();

    public void setStartTime(LocalDateTime startTime);

    public LocalDateTime getDeadline();

    public void setDeadline(LocalDateTime deadline);

    public void setId(Long id);

    @Nullable
    public Map<String, String> getAttributes();

    public void setAttributes(@Nullable Map<String, String> atriibutes);
}
