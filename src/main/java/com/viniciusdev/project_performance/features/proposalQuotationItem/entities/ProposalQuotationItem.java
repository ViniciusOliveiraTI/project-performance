package com.viniciusdev.project_performance.features.proposalQuotationItem.entities;

import com.viniciusdev.project_performance.features.proposalQuotation.entities.ProposalQuotation;
import jakarta.persistence.*;

import java.math.BigDecimal;
import java.util.Objects;
import java.util.UUID;

@Entity
@Table(name = "tb_proposal_quotation_item")
public class ProposalQuotationItem {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "proposal_quotation_item_id")
    private UUID id;

    @Column(name = "charge")
    private BigDecimal charge;

    @Column(name = "price")
    private BigDecimal price;

    @ManyToOne
    @JoinColumn(name = "proposal_quotation_id")
    private ProposalQuotation proposalQuotation;

    public ProposalQuotationItem() {}

    public ProposalQuotationItem(UUID id, ProposalQuotation proposalQuotation, BigDecimal charge, BigDecimal price) {
        this.id = id;
        this.proposalQuotation = proposalQuotation;
        this.charge = charge;
        this.price = price;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public ProposalQuotation getProposalQuotation() {
        return proposalQuotation;
    }

    public void setProposalQuotation(ProposalQuotation proposalQuotation) {
        this.proposalQuotation = proposalQuotation;
    }

    public BigDecimal getCharge() {
        return charge;
    }

    public void setCharge(BigDecimal charge) {
        this.charge = charge;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    @Override
    public boolean equals(Object object) {
        if (object == null || getClass() != object.getClass()) return false;
        ProposalQuotationItem that = (ProposalQuotationItem) object;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }

    @Override
    public String toString() {
        return "ProposalQuotationItem{" +
                "id=" + id +
                ", proposalQuotation=" + proposalQuotation +
                ", charge=" + charge +
                ", price=" + price +
                '}';
    }
}
