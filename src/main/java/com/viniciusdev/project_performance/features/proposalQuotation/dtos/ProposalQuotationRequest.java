package com.viniciusdev.project_performance.features.proposalQuotation.dtos;

import com.viniciusdev.project_performance.features.proposalQuotation.entities.ProposalQuotationStatus;

import java.time.LocalDate;

public record ProposalQuotationRequest(Long proposalId, LocalDate creationDate, Integer version, ProposalQuotationStatus status) { }
