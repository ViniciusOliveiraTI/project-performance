package com.viniciusdev.project_performance.features.proposal;

import com.viniciusdev.project_performance.features.proposal.dtos.ProposalRequest;
import com.viniciusdev.project_performance.features.proposal.dtos.ProposalResponse;
import com.viniciusdev.project_performance.features.proposal.entities.Proposal;
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

