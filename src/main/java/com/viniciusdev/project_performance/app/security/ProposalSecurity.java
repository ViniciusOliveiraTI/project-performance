package com.viniciusdev.project_performance.app.security;

import com.viniciusdev.project_performance.features.proposal.ProposalRepository;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component("proposalSecurity")
public class ProposalSecurity {

    private final ProposalRepository proposalRepository;

    public ProposalSecurity(ProposalRepository proposalRepository) {
        this.proposalRepository = proposalRepository;
    }

    public boolean isOwner(UUID proposalId, Authentication authentication) {

        UUID currentUserId = UUID.fromString(authentication.getName());

        return proposalRepository.findById(proposalId)
                .map(proposal -> proposal.getManager().getId().equals(currentUserId))
                .orElse(false);
    }

}
