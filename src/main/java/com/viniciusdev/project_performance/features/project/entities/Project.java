package com.viniciusdev.project_performance.features.project.entities;

import com.viniciusdev.project_performance.features.projectActivity.entities.ProjectActivity;
import com.viniciusdev.project_performance.features.proposal.entities.Proposal;
import jakarta.persistence.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Objects;
import java.util.Set;
import java.util.UUID;

@Entity
@Table(name = "tb_project")
public class Project {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "project_id")
    private UUID id;

    @Column(name = "approval_date")
    private LocalDate approvalDate;

    @Column(name = "approved_price")
    private BigDecimal approvedPrice;

    @Column(name = "status")
    @Enumerated(EnumType.STRING)
    private ProjectStatus status;

    @ManyToOne
    @JoinColumn(name = "proposal_id")
    private Proposal proposal;

    @OneToMany(mappedBy = "project")
    private Set<ProjectActivity> activities;

    public Project() {}

    public Project(UUID id, LocalDate approvalDate, BigDecimal approvedPrice, ProjectStatus status, Proposal proposal) {
        this.id = id;
        this.approvalDate = approvalDate;
        this.approvedPrice = approvedPrice;
        this.status = status;
        this.proposal = proposal;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public Set<ProjectActivity> getActivities() {
        return activities;
    }

    public void setActivities(Set<ProjectActivity> activities) {
        this.activities = activities;
    }

    public LocalDate getApprovalDate() {
        return approvalDate;
    }

    public void setApprovalDate(LocalDate approvalDate) {
        this.approvalDate = approvalDate;
    }

    public BigDecimal getApprovedPrice() {
        return approvedPrice;
    }

    public void setApprovedPrice(BigDecimal approvedPrice) {
        this.approvedPrice = approvedPrice;
    }

    public ProjectStatus getStatus() {
        return status;
    }

    public void setStatus(ProjectStatus status) {
        this.status = status;
    }

    public Proposal getProposal() {
        return proposal;
    }

    public void setProposal(Proposal proposal) {
        this.proposal = proposal;
    }

    @Override
    public boolean equals(Object object) {
        if (object == null || getClass() != object.getClass()) return false;
        Project project = (Project) object;
        return Objects.equals(id, project.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }

    @Override
    public String toString() {
        return "Project{" +
                "id=" + id +
                ", activities=" + activities +
                ", approvalDate=" + approvalDate +
                ", approvedPrice=" + approvedPrice +
                ", status=" + status +
                ", proposal=" + proposal +
                '}';
    }
}
