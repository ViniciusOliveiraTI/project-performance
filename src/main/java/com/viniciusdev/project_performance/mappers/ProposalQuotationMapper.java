package com.viniciusdev.project_performance.mappers;

import com.viniciusdev.project_performance.dtos.ProposalQuotationRequest;
import com.viniciusdev.project_performance.dtos.ProposalQuotationResponse;
import com.viniciusdev.project_performance.entities.ProposalQuotation;
import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(componentModel = "spring")
public interface ProposalQuotationMapper {
    ProposalQuotation requestToEntity(ProposalQuotationRequest proposalRequest);
    ProposalQuotationResponse entityToResponse(ProposalQuotation proposal);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.SET_TO_NULL)
    void updateEntityFromRequest(@MappingTarget ProposalQuotation proposal, ProposalQuotationRequest proposalRequest);
}

