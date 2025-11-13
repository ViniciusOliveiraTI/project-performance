package com.viniciusdev.project_performance.features.proposalQuotation.entities;

import com.viniciusdev.project_performance.features.proposal.entities.Proposal;
import com.viniciusdev.project_performance.features.proposalQuotationItem.entities.ProposalQuotationItem;
import jakarta.persistence.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Objects;
import java.util.Set;
import java.util.UUID;

@Entity
@Table(name = "tb_proposal_quotation")
public class ProposalQuotation {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "proposal_quotation_id")
    private UUID id;

    @Column(name = "creation_date")
    private LocalDate creationDate;

    @Column(name = "version")
    private Integer version;

    @Column(name = "status")
    @Enumerated(EnumType.STRING)
    private ProposalQuotationStatus status;

    @ManyToOne
    @JoinColumn(name = "proposal_id")
    private Proposal proposal;

    @OneToMany(mappedBy = "proposalQuotation")
    private Set<ProposalQuotationItem> proposalQuotationItems;

    public ProposalQuotation() {}

    public ProposalQuotation(UUID id, LocalDate creationDate, Integer version, ProposalQuotationStatus status, Proposal proposal) {
        this.id = id;
        this.creationDate = creationDate;
        this.version = version;
        this.status = status;
        this.proposal = proposal;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public LocalDate getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(LocalDate creationDate) {
        this.creationDate = creationDate;
    }

    public Integer getVersion() {
        return version;
    }

    public void setVersion(Integer version) {
        this.version = version;
    }

    public ProposalQuotationStatus getStatus() {
        return status;
    }

    public void setStatus(ProposalQuotationStatus status) {
        this.status = status;
    }

    public Proposal getProposal() {
        return proposal;
    }

    public void setProposal(Proposal proposal) {
        this.proposal = proposal;
    }

    public Set<ProposalQuotationItem> getProposalQuotationItems() {
        return proposalQuotationItems;
    }

    public void setProposalQuotationItems(Set<ProposalQuotationItem> proposalQuotationItems) {
        this.proposalQuotationItems = proposalQuotationItems;
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
                ", creationDate=" + creationDate +
                ", version=" + version +
                ", status=" + status +
                ", proposal=" + proposal +
                ", proposalQuotationItems=" + proposalQuotationItems +
                '}';
    }
}
