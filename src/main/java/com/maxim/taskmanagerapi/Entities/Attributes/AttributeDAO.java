package com.maxim.taskmanagerapi.Entities.Attributes;

import com.maxim.taskmanagerapi.Entities.Tasks.DAOs.TaskDAO;
import jakarta.persistence.*;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "attributes")
@NoArgsConstructor
public class AttributeDAO {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "label")
    private String key;

    @Column(name = "value")
    private String value;

    @JoinColumn(name = "task_id")
    @ManyToOne(fetch = FetchType.LAZY)
    private TaskDAO task_id;

    public AttributeDAO(String key, String value, TaskDAO task_id) {
        this.key = key;
        this.value = value;
        this.task_id = task_id;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
