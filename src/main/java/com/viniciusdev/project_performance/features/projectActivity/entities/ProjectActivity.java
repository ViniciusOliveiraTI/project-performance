package com.viniciusdev.project_performance.features.projectActivity.entities;

import com.viniciusdev.project_performance.features.project.entities.Project;
import com.viniciusdev.project_performance.features.projectActivityItem.entities.ProjectActivityItem;
import jakarta.persistence.*;

import java.time.LocalDate;
import java.time.LocalDate;
import java.util.Objects;
import java.util.Set;
import java.util.UUID;

@Entity
@Table(name = "tb_project_activity")
public class ProjectActivity {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "project_activity_id")
    private UUID id;

    @Column(name = "description")
    private String description;

    @Column(name = "expected_start_date")
    private LocalDate expectedStartDate;

    @Column(name = "expected_end_date")
    private LocalDate expectedEndDate;

    @Column(name = "status")
    @Enumerated(EnumType.STRING)
    private ProjectActivityStatus status;

    @ManyToOne
    @JoinColumn(name = "project_id")
    private Project project;

    @OneToMany(mappedBy = "projectActivity")
    private Set<ProjectActivityItem> projectActivityItems;

    public ProjectActivity() {}

    public ProjectActivity(UUID id, String description, LocalDate expectedStartDate, LocalDate expectedEndDate, ProjectActivityStatus status, Project project) {
        this.id = id;
        this.description = description;
        this.expectedStartDate = expectedStartDate;
        this.expectedEndDate = expectedEndDate;
        this.status = status;
        this.project = project;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
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

    public Project getProject() {
        return project;
    }

    public void setProject(Project project) {
        this.project = project;
    }

    public Set<ProjectActivityItem> getItems() {
        return projectActivityItems;
    }

    public void setItems(Set<ProjectActivityItem> projectActivityItems) {
        this.projectActivityItems = projectActivityItems;
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
                ", description='" + description + '\'' +
                ", expectedStartDate=" + expectedStartDate +
                ", expectedEndDate=" + expectedEndDate +
                ", status=" + status +
                ", project=" + project +
                ", projectActivityItems=" + projectActivityItems +
                '}';
    }
}
