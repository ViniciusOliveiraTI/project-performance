package com.viniciusdev.project_performance.features.proposalQuotation;

import com.viniciusdev.project_performance.common.exception.DataIntegrityException;
import com.viniciusdev.project_performance.common.exception.NotFoundException;
import com.viniciusdev.project_performance.features.proposal.ProposalRepository;
import com.viniciusdev.project_performance.features.proposal.entities.Proposal;
import com.viniciusdev.project_performance.features.proposalQuotation.dtos.ProposalQuotationRequest;
import com.viniciusdev.project_performance.features.proposalQuotation.dtos.ProposalQuotationResponse;
import com.viniciusdev.project_performance.features.proposalQuotation.entities.ProposalQuotation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProposalQuotationService {

    @Autowired
    private ProposalRepository proposalRepository;

    @Autowired
    private ProposalQuotationRepository proposalQuotationRepository;

    @Autowired
    private ProposalQuotationMapper mapper;

    public List<ProposalQuotationResponse> findAll() {
        return proposalQuotationRepository.findAll()
                .stream()
                .map(mapper::entityToResponse).toList();
    }

    public ProposalQuotationResponse findById(Long id) {
        return mapper.entityToResponse(
                proposalQuotationRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Proposal Quotation with id '%d' not found".formatted(id))));
    }

    public ProposalQuotationResponse create(ProposalQuotationRequest proposalQuotationRequest) {

        Proposal proposal = proposalRepository
                .findById(proposalQuotationRequest.proposalId())
                .orElseThrow(() -> new NotFoundException("Proposal with id '%d' not found".formatted(proposalQuotationRequest.proposalId())));

        ProposalQuotation proposalQuotation = mapper.requestToEntity(proposalQuotationRequest);

        proposalQuotation.setProposal(proposal);

        ProposalQuotation saved = proposalQuotationRepository.save(proposalQuotation);

        return mapper.entityToResponse(saved);

    }

    public void deleteById(Long id) {
        if (!proposalQuotationRepository.existsById(id)) {
            throw new NotFoundException("Proposal Quotation with id '%d' not found".formatted(id));
        };
        try {
            proposalQuotationRepository.deleteById(id);
        }
        catch (DataIntegrityViolationException e) {
            throw new DataIntegrityException("Proposal Quotation with id '%d' cannot be deleted due to relations".formatted(id));
        }
    }

    public ProposalQuotationResponse update(ProposalQuotationRequest proposalQuotationRequest, Long id) {

        ProposalQuotation proposalQuotation = proposalQuotationRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Proposal Quotation with id '%d' not found".formatted(id)));

        mapper.updateEntityFromRequest(proposalQuotation, proposalQuotationRequest);

        Proposal proposal = proposalRepository.findById(proposalQuotationRequest.proposalId())
                .orElseThrow(() -> new NotFoundException("Proposal with id '%d' not found".formatted(proposalQuotationRequest.proposalId())));

        proposalQuotation.setProposal(proposal);

        return mapper.entityToResponse(proposalQuotationRepository.save(proposalQuotation));
    }
}
