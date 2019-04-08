package com.coderbd.entity;

import javax.persistence.*;
import java.util.Date;
import java.util.Objects;

@Entity
public class AssignTask {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "developer_id", nullable = false)
    private User developer;

    @ManyToOne
    @JoinColumn(name = "task_id", nullable = false)
    private Task task;

    @Temporal(TemporalType.TIMESTAMP)
    private Date assignDate;

    public AssignTask() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public User getDeveloper() {
        return developer;
    }

    public void setDeveloper(User developer) {
        this.developer = developer;
    }

    public Task getTask() {
        return task;
    }

    public void setTask(Task task) {
        this.task = task;
    }

    public Date getAssignDate() {
        return assignDate;
    }

    public void setAssignDate(Date assignDate) {
        this.assignDate = assignDate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AssignTask that = (AssignTask) o;
        return Objects.equals(id, that.id) &&
                Objects.equals(developer, that.developer) &&
                Objects.equals(task, that.task) &&
                Objects.equals(assignDate, that.assignDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, developer, task, assignDate);
    }
}
