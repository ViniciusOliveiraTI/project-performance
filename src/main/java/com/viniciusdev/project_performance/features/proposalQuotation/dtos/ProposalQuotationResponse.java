package com.viniciusdev.project_performance.features.proposalQuotation.dtos;

import com.viniciusdev.project_performance.features.proposal.dtos.ProposalResponse;
import com.viniciusdev.project_performance.features.proposal.entities.Proposal;
import com.viniciusdev.project_performance.features.proposalQuotation.entities.ProposalQuotationStatus;

import java.time.LocalDate;
import java.util.UUID;

public record ProposalQuotationResponse(UUID id, LocalDate creationDate, Integer version, ProposalQuotationStatus status, ProposalResponse proposal) { }
