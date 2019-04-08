package com.coderbd.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.Objects;

@Entity
public class TaskCriticalLevel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
//available, working,reviewedByTM, rejectByTM, approvedByTM, reviewedByPM, rejectedByPM, approvedByPM, complete.
    //easy=2, medium=4, hard=8, veryHard=16
    private String level;

    private double levelPoint;
    private String note;

    public TaskCriticalLevel() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }

    public double getLevelPoint() {
        return levelPoint;
    }

    public void setLevelPoint(double levelPoint) {
        this.levelPoint = levelPoint;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TaskCriticalLevel that = (TaskCriticalLevel) o;
        return Double.compare(that.getLevelPoint(), getLevelPoint()) == 0 &&
                Objects.equals(getId(), that.getId()) &&
                Objects.equals(getLevel(), that.getLevel()) &&
                Objects.equals(getNote(), that.getNote());
    }

    @Override
    public int hashCode() {

        return Objects.hash(getId(), getLevel(), getLevelPoint(), getNote());
    }
}
