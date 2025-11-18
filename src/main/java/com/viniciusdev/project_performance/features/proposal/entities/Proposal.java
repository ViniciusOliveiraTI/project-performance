package com.viniciusdev.project_performance.features.proposal.entities;

import com.viniciusdev.project_performance.features.customer.entities.Customer;
import com.viniciusdev.project_performance.features.project.entities.Project;
import com.viniciusdev.project_performance.features.proposalQuotation.entities.ProposalQuotation;
import com.viniciusdev.project_performance.features.user.entities.User;
import jakarta.persistence.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Objects;
import java.util.Set;
import java.util.UUID;

@Entity
@Table(name = "tb_proposal")
public class Proposal {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "proposal_id")
    private UUID id;

    @Column(name = "code")
    private Integer code;

    @Column(name = "description")
    private String description;

    @Column(name = "emission_date")
    private LocalDate emissionDate;

    @Column(name = "status")
    @Enumerated(EnumType.STRING)
    private ProposalStatus status;

    @Column(name = "offered_price")
    private BigDecimal offeredPrice;

    @ManyToOne
    @JoinColumn(name = "manager_id")
    private User manager;

    @ManyToOne
    @JoinColumn(name = "customer_id")
    private Customer customer;

    @OneToMany(mappedBy = "proposal")
    private Set<ProposalQuotation> proposalQuotations;

    @OneToMany(mappedBy = "proposal")
    private Set<Project> projects;

    public Proposal() {}


    public Proposal(UUID id, Integer code, String description, LocalDate emissionDate, ProposalStatus status, BigDecimal offeredPrice, User manager, Customer customer) {
        this.id = id;
        this.code = code;
        this.description = description;
        this.emissionDate = emissionDate;
        this.status = status;
        this.offeredPrice = offeredPrice;
        this.manager = manager;
        this.customer = customer;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
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

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public Set<ProposalQuotation> getProposalQuotations() {
        return proposalQuotations;
    }

    public void setProposalQuotations(Set<ProposalQuotation> proposalQuotations) {
        this.proposalQuotations = proposalQuotations;
    }

    public Set<Project> getProjects() {
        return projects;
    }

    public void setProjects(Set<Project> projects) {
        this.projects = projects;
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

    public User getManager() {
        return manager;
    }

    public void setManager(User manager) {
        this.manager = manager;
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
                ", customer=" + customer +
                ", proposalQuotations=" + proposalQuotations +
                ", projects=" + projects +
                ", status=" + status +
                ", offeredPrice=" + offeredPrice +
                '}';
    }
}
