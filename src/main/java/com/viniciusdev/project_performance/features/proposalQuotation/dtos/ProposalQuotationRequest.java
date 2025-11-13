package com.viniciusdev.project_performance.features.proposalQuotation.dtos;

import com.viniciusdev.project_performance.features.proposalQuotation.entities.ProposalQuotationStatus;

import java.time.LocalDate;
import java.util.UUID;

public record ProposalQuotationRequest(UUID proposalId, LocalDate creationDate, Integer version, ProposalQuotationStatus status) { }
