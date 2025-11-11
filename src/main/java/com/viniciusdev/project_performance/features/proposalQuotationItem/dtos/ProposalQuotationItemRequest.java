package com.viniciusdev.project_performance.features.proposalQuotationItem.dtos;

import java.math.BigDecimal;

public record ProposalQuotationItemRequest(BigDecimal price, BigDecimal charge, Long proposalQuotationId) { }
