package com.viniciusdev.project_performance.entities;

import com.viniciusdev.project_performance.enums.ProposalQuotationStatus;
import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.Objects;

@Entity
@Table(name = "tb_proposal_quotation")
public class ProposalQuotation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "proposal_id", nullable = false)
    private Proposal proposal;

    private LocalDate creationDate;
    private Integer version;

    @Enumerated(EnumType.STRING)
    private ProposalQuotationStatus status;

    public ProposalQuotation() {}

    public ProposalQuotation(Proposal proposal, LocalDate creationDate, Integer version, ProposalQuotationStatus status) {
        this.proposal = proposal;
        this.creationDate = creationDate;
        this.version = version;
        this.status = status;
    }

    public ProposalQuotationStatus getStatus() {
        return status;
    }

    public void setStatus(ProposalQuotationStatus status) {
        this.status = status;
    }

    public Integer getVersion() {
        return version;
    }

    public void setVersion(Integer version) {
        this.version = version;
    }

    public LocalDate getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(LocalDate creationDate) {
        this.creationDate = creationDate;
    }

    public Proposal getProposal() {
        return proposal;
    }

    public void setProposal(Proposal proposal) {
        this.proposal = proposal;
    }

    public Long getId() {
        return id;
    }

    @Override
    public boolean equals(Object object) {
        if (object == null || getClass() != object.getClass()) return false;
        ProposalQuotation that = (ProposalQuotation) object;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }

    @Override
    public String toString() {
        return "ProposalQuotation{" +
                "id=" + id +
                ", proposal=" + proposal +
                ", creationDate=" + creationDate +
                ", version=" + version +
                ", status=" + status +
                '}';
    }
}
