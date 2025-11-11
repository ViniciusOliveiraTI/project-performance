package com.viniciusdev.project_performance.features.proposalQuotationItem;

import com.viniciusdev.project_performance.features.proposalQuotationItem.dtos.ProposalQuotationItemRequest;
import com.viniciusdev.project_performance.features.proposalQuotationItem.dtos.ProposalQuotationItemResponse;
import com.viniciusdev.project_performance.features.proposalQuotationItem.entities.ProposalQuotationItem;
import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(componentModel = "spring")
public interface ProposalQuotationItemMapper {
    ProposalQuotationItem requestToEntity(ProposalQuotationItemRequest proposalQuotationItemRequest);
    ProposalQuotationItemResponse entityToResponse(ProposalQuotationItem proposalQuotationItem);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.SET_TO_NULL)
    void updateEntityFromRequest(@MappingTarget ProposalQuotationItem proposalQuotationItem, ProposalQuotationItemRequest proposalQuotationItemRequest);
}

