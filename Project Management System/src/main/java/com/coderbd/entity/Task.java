package com.coderbd.entity;

import javax.persistence.*;
import java.util.Objects;
import java.util.Set;

@Entity
public class Task {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;

    private String description;

    @ManyToOne
    @JoinColumn(name = "tc_level_id")
    private TaskCriticalLevel taskCriticalLevel;

    @ManyToOne
    @JoinColumn(name = "pmodule_id", nullable = false)
    private ProjectModule projectModule;

    @ManyToOne
    @JoinColumn(name = "company_id", nullable = false)
    private Company company;

    private boolean dependentTaskStatus;

    @ManyToOne
    @JoinColumn(nullable = true,name = "parent_task_id")
    private Task parentTask;

    @OneToMany(mappedBy = "parentTask")
    private Set<Task> dependentTasks;

    public Task() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public TaskCriticalLevel getTaskCriticalLevel() {
        return taskCriticalLevel;
    }

    public void setTaskCriticalLevel(TaskCriticalLevel taskCriticalLevel) {
        this.taskCriticalLevel = taskCriticalLevel;
    }

    public ProjectModule getProjectModule() {
        return projectModule;
    }

    public void setProjectModule(ProjectModule projectModule) {
        this.projectModule = projectModule;
    }

    public Company getCompany() {
        return company;
    }

    public void setCompany(Company company) {
        this.company = company;
    }

    public boolean isDependentTaskStatus() {
        return dependentTaskStatus;
    }

    public void setDependentTaskStatus(boolean dependentTaskStatus) {
        this.dependentTaskStatus = dependentTaskStatus;
    }

    public Task getParentTask() {
        return parentTask;
    }

    public void setParentTask(Task parentTask) {
        this.parentTask = parentTask;
    }

    public Set<Task> getDependentTasks() {
        return dependentTasks;
    }

    public void setDependentTasks(Set<Task> dependentTasks) {
        this.dependentTasks = dependentTasks;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Task task = (Task) o;
        return dependentTaskStatus == task.dependentTaskStatus &&
                Objects.equals(id, task.id) &&
                Objects.equals(title, task.title) &&
                Objects.equals(description, task.description) &&
                Objects.equals(taskCriticalLevel, task.taskCriticalLevel) &&
                Objects.equals(projectModule, task.projectModule) &&
                Objects.equals(company, task.company) &&
                Objects.equals(parentTask, task.parentTask) &&
                Objects.equals(dependentTasks, task.dependentTasks);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, title, description, taskCriticalLevel, projectModule, company, dependentTaskStatus, parentTask, dependentTasks);
    }
}
