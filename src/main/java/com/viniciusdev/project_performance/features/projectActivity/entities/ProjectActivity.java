package com.viniciusdev.project_performance.features.projectActivity.entities;

import com.viniciusdev.project_performance.features.project.entities.Project;
import jakarta.persistence.*;

import java.time.LocalDate;
import java.time.LocalDate;
import java.util.Objects;

@Entity
@Table(name = "tb_project_activity")
public class ProjectActivity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "project_id")
    private Project project;
    private String description;
    private LocalDate expectedStartDate;
    private LocalDate expectedEndDate;
    private ProjectActivityStatus status;

    public ProjectActivity() {}

    public ProjectActivity(Project project, String description, LocalDate expectedStartDate, LocalDate expectedEndDate, ProjectActivityStatus status) {
        this.project = project;
        this.description = description;
        this.expectedStartDate = expectedStartDate;
        this.expectedEndDate = expectedEndDate;
        this.status = status;
    }

    public Long getId() {
        return id;
    }

    public Project getProject() {
        return project;
    }

    public void setProject(Project project) {
        this.project = project;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public LocalDate getExpectedStartDate() {
        return expectedStartDate;
    }

    public void setExpectedStartDate(LocalDate expectedStartDate) {
        this.expectedStartDate = expectedStartDate;
    }

    public LocalDate getExpectedEndDate() {
        return expectedEndDate;
    }

    public void setExpectedEndDate(LocalDate expectedEndDate) {
        this.expectedEndDate = expectedEndDate;
    }

    public ProjectActivityStatus getStatus() {
        return status;
    }

    public void setStatus(ProjectActivityStatus status) {
        this.status = status;
    }

    @Override
    public boolean equals(Object object) {
        if (object == null || getClass() != object.getClass()) return false;
        ProjectActivity that = (ProjectActivity) object;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }

    @Override
    public String toString() {
        return "ProjectActivity{" +
                "id=" + id +
                ", project=" + project +
                ", description='" + description + '\'' +
                ", expectedStartDate=" + expectedStartDate +
                ", expectedEndDate=" + expectedEndDate +
                ", status=" + status +
                '}';
    }
}
