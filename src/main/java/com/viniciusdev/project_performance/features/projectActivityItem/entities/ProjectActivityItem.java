package com.viniciusdev.project_performance.features.projectActivityItem.entities;

import com.viniciusdev.project_performance.features.projectActivity.entities.ProjectActivity;
import jakarta.persistence.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Objects;

@Entity
@Table(name = "tb_project_projectActivity_item")
public class ProjectActivityItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "projectActivity_id")
    private ProjectActivity projectActivity;

    private LocalDate conclusionDate;
    private BigDecimal charge;
    private BigDecimal cost;

    public ProjectActivityItem() {}

    public ProjectActivityItem(ProjectActivity projectActivity, LocalDate conclusionDate, BigDecimal charge, BigDecimal cost) {
        this.projectActivity = projectActivity;
        this.conclusionDate = conclusionDate;
        this.charge = charge;
        this.cost = cost;
    }

    public Long getId() {
        return id;
    }

    public ProjectActivity getProjectActivity() {
        return projectActivity;
    }

    public void setProjectActivity(ProjectActivity projectActivity) {
        this.projectActivity = projectActivity;
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
                ", projectActivity=" + projectActivity +
                ", conclusionDate=" + conclusionDate +
                ", charge=" + charge +
                ", cost=" + cost +
                '}';
    }
}
