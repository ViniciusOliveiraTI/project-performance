package com.viniciusdev.project_performance.features.proposalQuotationItem.entities;

import com.viniciusdev.project_performance.features.proposalQuotation.entities.ProposalQuotation;
import jakarta.persistence.*;

import java.math.BigDecimal;
import java.util.Objects;

@Entity
@Table(name = "tb_proposal_quotation_item")
public class ProposalQuotationItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "proposal_quotation_id", nullable = false)
    private ProposalQuotation proposalQuotation;

    private BigDecimal charge;
    private BigDecimal price;

    public ProposalQuotationItem() {}

    public ProposalQuotationItem(BigDecimal price, BigDecimal charge, ProposalQuotation proposalQuotation) {
        this.price = price;
        this.charge = charge;
        this.proposalQuotation = proposalQuotation;
    }

    public Long getId() {
        return id;
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
