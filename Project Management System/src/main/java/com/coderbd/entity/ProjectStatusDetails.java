package com.coderbd.entity;

import javax.persistence.*;
import java.util.Date;
import java.util.Objects;

@Entity
public class ProjectStatusDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "project_status_id", nullable = false)
    private ProjectStatus projectStatus;

    @Temporal(TemporalType.TIMESTAMP)
    private Date statusChangeDate;

    public ProjectStatusDetails() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public ProjectStatus getProjectStatus() {
        return projectStatus;
    }

    public void setProjectStatus(ProjectStatus projectStatus) {
        this.projectStatus = projectStatus;
    }

    public Date getStatusChangeDate() {
        return statusChangeDate;
    }

    public void setStatusChangeDate(Date statusChangeDate) {
        this.statusChangeDate = statusChangeDate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ProjectStatusDetails that = (ProjectStatusDetails) o;
        return Objects.equals(getId(), that.getId()) &&
                Objects.equals(getProjectStatus(), that.getProjectStatus()) &&
                Objects.equals(getStatusChangeDate(), that.getStatusChangeDate());
    }

    @Override
    public int hashCode() {

        return Objects.hash(getId(), getProjectStatus(), getStatusChangeDate());
    }

}
