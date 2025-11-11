package com.viniciusdev.project_performance.dtos;

import com.viniciusdev.project_performance.entities.Proposal;
import com.viniciusdev.project_performance.entities.ProposalQuotation;
import com.viniciusdev.project_performance.enums.ProposalQuotationStatus;

import java.math.BigDecimal;
import java.time.LocalDate;

public record ProposalQuotationItemResponse(Long id, BigDecimal price, BigDecimal charge, ProposalQuotation proposalQuotation) { }
