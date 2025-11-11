package com.viniciusdev.project_performance.dtos;

import com.viniciusdev.project_performance.entities.Proposal;
import com.viniciusdev.project_performance.enums.ProposalQuotationStatus;

import java.time.LocalDate;

public record ProposalQuotationRequest(Long proposalId, LocalDate creationDate, Integer version, ProposalQuotationStatus status) { }
