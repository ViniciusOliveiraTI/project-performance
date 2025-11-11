package com.viniciusdev.project_performance.features.proposalQuotation.dtos;

import com.viniciusdev.project_performance.features.proposal.entities.Proposal;
import com.viniciusdev.project_performance.features.proposalQuotation.entities.ProposalQuotationStatus;

import java.time.LocalDate;

public record ProposalQuotationResponse(Long id, LocalDate creationDate, Integer version, ProposalQuotationStatus status, Proposal proposal) { }
