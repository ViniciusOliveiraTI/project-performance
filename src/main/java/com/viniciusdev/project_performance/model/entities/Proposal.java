package com.viniciusdev.project_performance.model.entities;

import com.viniciusdev.project_performance.model.enums.ProposalStatus;
import jakarta.persistence.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Objects;

@Entity
@Table(name = "tb_proposal")
public class Proposal {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Integer code;
    private String description;
    // private User manager;
    // private Customer customer;
    private LocalDate emissionDate;

    @Enumerated(EnumType.STRING)
    private ProposalStatus status;
    private BigDecimal offeredPrice;

    public Proposal() {}

    public Proposal(Integer code, String description, LocalDate emissionDate, ProposalStatus status, BigDecimal offeredPrice) {
        this.code = code;
        this.description = description;
        this.emissionDate = emissionDate;
        this.status = status;
        this.offeredPrice = offeredPrice;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public LocalDate getEmissionDate() {
        return emissionDate;
    }

    public void setEmissionDate(LocalDate emissionDate) {
        this.emissionDate = emissionDate;
    }

    public ProposalStatus getStatus() {
        return status;
    }

    public void setStatus(ProposalStatus status) {
        this.status = status;
    }

    public BigDecimal getOfferedPrice() {
        return offeredPrice;
    }

    public void setOfferedPrice(BigDecimal offeredPrice) {
        this.offeredPrice = offeredPrice;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Proposal proposal = (Proposal) o;
        return Objects.equals(id, proposal.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }

    @Override
    public String toString() {
        return "Proposal{" +
                "id=" + id +
                ", code=" + code +
                ", description='" + description + '\'' +
                ", emissionDate=" + emissionDate +
                ", offeredPrice=" + offeredPrice +
                '}';
    }
}
