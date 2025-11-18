package com.viniciusdev.project_performance.features.proposalQuotation;

import com.viniciusdev.project_performance.app.exception.DataIntegrityException;
import com.viniciusdev.project_performance.app.exception.NotFoundException;
import com.viniciusdev.project_performance.features.proposal.ProposalMapper;
import com.viniciusdev.project_performance.features.proposal.ProposalRepository;
import com.viniciusdev.project_performance.features.proposal.dtos.ProposalResponse;
import com.viniciusdev.project_performance.features.proposal.entities.Proposal;
import com.viniciusdev.project_performance.features.proposalQuotation.dtos.ProposalQuotationRequest;
import com.viniciusdev.project_performance.features.proposalQuotation.dtos.ProposalQuotationResponse;
import com.viniciusdev.project_performance.features.proposalQuotation.entities.ProposalQuotation;
import com.viniciusdev.project_performance.features.proposalQuotationItem.ProposalQuotationItemMapper;
import com.viniciusdev.project_performance.features.proposalQuotationItem.dtos.ProposalQuotationItemResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class ProposalQuotationService {

    @Autowired
    private ProposalRepository proposalRepository;

    @Autowired
    private ProposalQuotationRepository proposalQuotationRepository;

    @Autowired
    private ProposalQuotationMapper proposalQuotationMapper;

    @Autowired
    private ProposalMapper proposalMapper;

    @Autowired
    private ProposalQuotationItemMapper proposalQuotationItemMapper;

    public List<ProposalQuotationResponse> findAll() {
        return proposalQuotationRepository.findAll()
                .stream()
                .map(proposalQuotationMapper::entityToResponse).toList();
    }

    public ProposalQuotationResponse findById(UUID id) {
        return proposalQuotationMapper.entityToResponse(
                proposalQuotationRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Proposal Quotation with id '%s' not found".formatted(id))));
    }

    public ProposalQuotationResponse create(ProposalQuotationRequest proposalQuotationRequest) {

        Proposal proposal = proposalRepository
                .findById(proposalQuotationRequest.proposalId())
                .orElseThrow(() -> new NotFoundException("Proposal with id '%s' not found".formatted(proposalQuotationRequest.proposalId())));

        ProposalQuotation proposalQuotation = proposalQuotationMapper.requestToEntity(proposalQuotationRequest);

        proposalQuotation.setProposal(proposal);

        ProposalQuotation saved = proposalQuotationRepository.save(proposalQuotation);

        return proposalQuotationMapper.entityToResponse(saved);

    }

    public void deleteById(UUID id) {
        if (!proposalQuotationRepository.existsById(id)) {
            throw new NotFoundException("Proposal Quotation with id '%s' not found".formatted(id));
        };
        try {
            proposalQuotationRepository.deleteById(id);
        }
        catch (DataIntegrityViolationException e) {
            throw new DataIntegrityException("Proposal Quotation with id '%s' cannot be deleted due to relations".formatted(id));
        }
    }

    public ProposalQuotationResponse update(ProposalQuotationRequest proposalQuotationRequest, UUID id) {

        ProposalQuotation proposalQuotation = proposalQuotationRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Proposal Quotation with id '%s' not found".formatted(id)));

        proposalQuotationMapper.updateEntityFromRequest(proposalQuotation, proposalQuotationRequest);

        Proposal proposal = proposalRepository.findById(proposalQuotationRequest.proposalId())
                .orElseThrow(() -> new NotFoundException("Proposal with id '%s' not found".formatted(proposalQuotationRequest.proposalId())));

        proposalQuotation.setProposal(proposal);

        return proposalQuotationMapper.entityToResponse(proposalQuotationRepository.save(proposalQuotation));
    }

    public List<ProposalQuotationItemResponse> findAllProposalQuotationItem(UUID id) {

        ProposalQuotation proposalQuotation = proposalQuotationRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Proposal Quotation with id '%s' not found".formatted(id)));

        return proposalQuotation.getProposalQuotationItems()
                .stream()
                .map(proposalQuotationItemMapper::entityToResponse)
                .toList();
    }

    public ProposalResponse findProposal(UUID id) {

        ProposalQuotation proposalQuotation = proposalQuotationRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Proposal Quotation with id '%s' not found".formatted(id)));

        return proposalMapper.entityToResponse(proposalQuotation.getProposal());

    }
}
