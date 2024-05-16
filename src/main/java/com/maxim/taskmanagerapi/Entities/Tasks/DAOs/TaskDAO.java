package com.maxim.taskmanagerapi.Entities.Tasks.DAOs;


import com.maxim.taskmanagerapi.Entities.Attributes.AttributeDAO;
import com.maxim.taskmanagerapi.Entities.Users.UserDAO;
import com.maxim.taskmanagerapi.Entities.Tasks.Common.Status;
import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "tasks")
public class TaskDAO {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "label",nullable = false)
    private String label;

    @Column(name = "description")
    private String description;

    @Column(name = "status")
    @Enumerated(EnumType.STRING)
    private Status status;

    @ManyToOne
    @JoinColumn(name = "owner_id", nullable = false)
    private UserDAO ownerId;

    @ManyToMany()
    @JoinTable(name = "task_to_task")
    private List<TaskDAO> subTasks;

    @Column(name = "start")
    @Temporal(TemporalType.TIMESTAMP)
    private LocalDateTime start;

    @Column(name = "deadline")
    @Temporal(TemporalType.TIMESTAMP)
    private LocalDateTime deadline;

    @OneToMany
    @JoinColumn(name = "task_id")
    private List<AttributeDAO> attributes;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public UserDAO getOwnerId() {
        return ownerId;
    }

    public void setOwnerId(UserDAO ownerId) {
        this.ownerId = ownerId;
    }

    public List<TaskDAO> getSubTasks() {
        return subTasks;
    }

    public void setSubTasks(List<TaskDAO> subTasks) {
        this.subTasks = subTasks;
    }

    public LocalDateTime getStart() {
        return start;
    }

    public void setStart(LocalDateTime start) {
        this.start = start;
    }

    public LocalDateTime getDeadline() {
        return deadline;
    }

    public void setDeadline(LocalDateTime deadline) {
        this.deadline = deadline;
    }

    public List<AttributeDAO> getAttributes() {
        return attributes;
    }

    public void setAttributes(List<AttributeDAO> attributes) {
        this.attributes = attributes;
    }
}
