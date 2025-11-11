package com.viniciusdev.project_performance.mappers;

import com.viniciusdev.project_performance.dtos.ProposalQuotationItemRequest;
import com.viniciusdev.project_performance.dtos.ProposalQuotationItemResponse;
import com.viniciusdev.project_performance.entities.ProposalQuotationItem;
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

