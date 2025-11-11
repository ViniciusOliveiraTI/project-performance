package com.viniciusdev.project_performance.features.proposalQuotationItem.dtos;

import com.viniciusdev.project_performance.features.proposalQuotation.entities.ProposalQuotation;

import java.math.BigDecimal;

public record ProposalQuotationItemResponse(Long id, BigDecimal price, BigDecimal charge, ProposalQuotation proposalQuotation) { }
