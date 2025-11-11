package com.viniciusdev.project_performance.dtos;

import com.viniciusdev.project_performance.entities.Customer;
import com.viniciusdev.project_performance.entities.Proposal;
import com.viniciusdev.project_performance.enums.ProposalQuotationStatus;
import com.viniciusdev.project_performance.enums.ProposalStatus;

import java.math.BigDecimal;
import java.time.LocalDate;

public record ProposalQuotationResponse(Long id, LocalDate creationDate, Integer version, ProposalQuotationStatus status, Proposal proposal) { }
