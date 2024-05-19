package com.maxim.taskmanagerapi.Entities.Tasks.DAOs;


import com.maxim.taskmanagerapi.Entities.Attributes.AttributeDAO;
import com.maxim.taskmanagerapi.Entities.Users.UserDAO;
import com.maxim.taskmanagerapi.Entities.Tasks.Common.Status;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "tasks")
public class TaskDAO {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Setter
    @Getter
    private Long id;

    @Setter
    @Getter
    @Column(name = "label",nullable = false)
    private String label;

    @Setter
    @Getter
    @Column(name = "description")
    private String description;

    @Setter
    @Getter
    @Column(name = "status")
    @Enumerated(EnumType.STRING)
    private Status status;

    @Setter
    @Getter
    @ManyToOne
    @JoinColumn(name = "owner_id", nullable = false)
    private UserDAO ownerId;

    @Setter
    @Getter
    @ManyToMany()
    @JoinTable(name = "task_to_task")
    private List<TaskDAO> subTasks;

    @Setter
    @Getter
    @Column(name = "start")
    @Temporal(TemporalType.TIMESTAMP)
    private LocalDateTime start;

    @Setter
    @Getter
    @Column(name = "deadline")
    @Temporal(TemporalType.TIMESTAMP)
    private LocalDateTime deadline;

    @Setter
    @Getter
    @OneToMany
    @JoinColumn(name = "task_id")
    private List<AttributeDAO> attributes;

    public UserDAO getOwnerId() {
        return ownerId;
    }

    public void setOwnerId(UserDAO ownerId) {
        this.ownerId = ownerId;
    }
}
