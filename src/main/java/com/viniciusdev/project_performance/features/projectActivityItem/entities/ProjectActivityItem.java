package com.viniciusdev.project_performance.features.projectActivityItem.entities;

import com.viniciusdev.project_performance.features.projectActivity.entities.ProjectActivity;
import jakarta.persistence.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Objects;
import java.util.UUID;

@Entity
@Table(name = "tb_project_projectActivity_item")
public class ProjectActivityItem {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "project_activity_item_id")
    private UUID id;

    @Column(name = "conclusion_date")
    private LocalDate conclusionDate;

    @Column(name = "charge")
    private BigDecimal charge;

    @Column(name = "cost")
    private BigDecimal cost;

    @ManyToOne
    @JoinColumn(name = "project_activity_id")
    private ProjectActivity projectActivity;

    public ProjectActivityItem() {}

    public ProjectActivityItem(UUID id, LocalDate conclusionDate, BigDecimal charge, BigDecimal cost, ProjectActivity projectActivity) {
        this.id = id;
        this.conclusionDate = conclusionDate;
        this.charge = charge;
        this.cost = cost;
        this.projectActivity = projectActivity;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public LocalDate getConclusionDate() {
        return conclusionDate;
    }

    public void setConclusionDate(LocalDate conclusionDate) {
        this.conclusionDate = conclusionDate;
    }

    public BigDecimal getCharge() {
        return charge;
    }

    public void setCharge(BigDecimal charge) {
        this.charge = charge;
    }

    public BigDecimal getCost() {
        return cost;
    }

    public void setCost(BigDecimal cost) {
        this.cost = cost;
    }

    public ProjectActivity getProjectActivity() {
        return projectActivity;
    }

    public void setProjectActivity(ProjectActivity projectActivity) {
        this.projectActivity = projectActivity;
    }

    @Override
    public boolean equals(Object object) {
        if (object == null || getClass() != object.getClass()) return false;
        ProjectActivityItem that = (ProjectActivityItem) object;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }

    @Override
    public String toString() {
        return "ProjectActivityItem{" +
                "id=" + id +
                ", conclusionDate=" + conclusionDate +
                ", charge=" + charge +
                ", cost=" + cost +
                ", projectActivity=" + projectActivity +
                '}';
    }
}
