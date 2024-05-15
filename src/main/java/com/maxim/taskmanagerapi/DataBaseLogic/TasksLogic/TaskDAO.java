package com.maxim.taskmanagerapi.DataBaseLogic.TasksLogic;


import jakarta.persistence.*;

@Entity
@Table(name = "tasks")
public class TaskDAO {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @Column(name = "description")
    String description;

    @Column(name = "owner_id")
    Long ownerId;


    //TODO add many-to-many

}
