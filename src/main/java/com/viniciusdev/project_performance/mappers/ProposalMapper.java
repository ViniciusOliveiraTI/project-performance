package com.viniciusdev.project_performance.mappers;

import com.viniciusdev.project_performance.dtos.ProposalRequest;
import com.viniciusdev.project_performance.dtos.ProposalResponse;
import com.viniciusdev.project_performance.entities.Proposal;
import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(componentModel = "spring")
public interface ProposalMapper {
    Proposal requestToEntity(ProposalRequest proposalRequest);
    ProposalResponse entityToResponse(Proposal proposal);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.SET_TO_NULL)
    void updateEntityFromRequest(@MappingTarget Proposal proposal, ProposalRequest proposalRequest);
}

