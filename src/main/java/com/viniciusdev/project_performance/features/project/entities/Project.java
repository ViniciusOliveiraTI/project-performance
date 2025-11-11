package com.viniciusdev.project_performance.features.project.entities;

import com.viniciusdev.project_performance.features.proposal.entities.Proposal;
import jakarta.persistence.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Objects;

@Entity
@Table(name = "tb_project")
public class Project {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "proposal_id", nullable = false)
    private Proposal proposal;

    private LocalDate approvalDate;
    private BigDecimal approvedPrice;

    @Enumerated(EnumType.STRING)
    private ProjectStatus status;

    public Project() {}

    public Project(ProjectStatus status, BigDecimal approvedPrice, LocalDate approvalDate, Proposal proposal) {
        this.status = status;
        this.approvedPrice = approvedPrice;
        this.approvalDate = approvalDate;
        this.proposal = proposal;
    }

    public Long getId() {
        return id;
    }

    public Proposal getProposal() {
        return proposal;
    }

    public void setProposal(Proposal proposal) {
        this.proposal = proposal;
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
                ", proposal=" + proposal +
                ", approvalDate=" + approvalDate +
                ", approvedPrice=" + approvedPrice +
                ", status=" + status +
                '}';
    }
}
