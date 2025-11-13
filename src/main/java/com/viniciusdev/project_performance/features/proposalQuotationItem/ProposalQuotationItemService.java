package com.viniciusdev.project_performance.features.proposalQuotationItem;

import com.viniciusdev.project_performance.common.exception.DataIntegrityException;
import com.viniciusdev.project_performance.common.exception.NotFoundException;
import com.viniciusdev.project_performance.features.proposalQuotation.ProposalQuotationMapper;
import com.viniciusdev.project_performance.features.proposalQuotation.dtos.ProposalQuotationResponse;
import com.viniciusdev.project_performance.features.proposalQuotationItem.dtos.ProposalQuotationItemRequest;
import com.viniciusdev.project_performance.features.proposalQuotationItem.dtos.ProposalQuotationItemResponse;
import com.viniciusdev.project_performance.features.proposalQuotation.entities.ProposalQuotation;
import com.viniciusdev.project_performance.features.proposalQuotationItem.entities.ProposalQuotationItem;
import com.viniciusdev.project_performance.features.proposalQuotation.ProposalQuotationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class ProposalQuotationItemService {

    @Autowired
    private ProposalQuotationRepository proposalQuotationRepository;

    @Autowired
    private ProposalQuotationItemRepository proposalQuotationItemRepository;

    @Autowired
    private ProposalQuotationItemMapper proposalQuotationItemMapper;

    @Autowired
    private ProposalQuotationMapper proposalQuotationMapper;

    public List<ProposalQuotationItemResponse> findAll() {
        return proposalQuotationItemRepository.findAll()
                .stream()
                .map(proposalQuotationItemMapper::entityToResponse).toList();
    }

    public ProposalQuotationItemResponse findById(UUID id) {
        return proposalQuotationItemMapper.entityToResponse(
                proposalQuotationItemRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Proposal Quotation Item with id '%d' not found".formatted(id))));
    }

    public ProposalQuotationItemResponse create(ProposalQuotationItemRequest proposalQuotationItemRequest) {

        ProposalQuotation proposalQuotation = proposalQuotationRepository
                .findById(proposalQuotationItemRequest.proposalQuotationId())
                .orElseThrow(() -> new NotFoundException("Proposal Quotation with id '%d' not found".formatted(proposalQuotationItemRequest.proposalQuotationId())));

        ProposalQuotationItem proposalQuotationItem = proposalQuotationItemMapper.requestToEntity(proposalQuotationItemRequest);

        proposalQuotationItem.setProposalQuotation(proposalQuotation);

        ProposalQuotationItem saved = proposalQuotationItemRepository.save(proposalQuotationItem);

        return proposalQuotationItemMapper.entityToResponse(saved);

    }

    public void deleteById(UUID id) {
        if (!proposalQuotationItemRepository.existsById(id)) {
            throw new NotFoundException("Proposal Quotation Item with id '%d' not found".formatted(id));
        };
        try {
            proposalQuotationItemRepository.deleteById(id);
        }
        catch (DataIntegrityViolationException e) {
            throw new DataIntegrityException("Proposal Quotation Item with id '%d' cannot be deleted due to relations".formatted(id));
        }
    }

    public ProposalQuotationItemResponse update(ProposalQuotationItemRequest proposalQuotationItemRequest, UUID id) {

        ProposalQuotationItem proposalQuotationItem = proposalQuotationItemRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Proposal Quotation Item with id '%d' not found".formatted(id)));

        proposalQuotationItemMapper.updateEntityFromRequest(proposalQuotationItem, proposalQuotationItemRequest);

        ProposalQuotation proposalQuotation = proposalQuotationRepository.findById(proposalQuotationItemRequest.proposalQuotationId())
                .orElseThrow(() -> new NotFoundException("Proposal Quotation with id '%d' not found".formatted(proposalQuotationItemRequest.proposalQuotationId())));

        proposalQuotationItem.setProposalQuotation(proposalQuotation);

        return proposalQuotationItemMapper.entityToResponse(proposalQuotationItemRepository.save(proposalQuotationItem));
    }

    public ProposalQuotationResponse findQuotation(UUID id) {

        ProposalQuotationItem proposalQuotationItem = proposalQuotationItemRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Proposal Quotation Item with id '%d' not found".formatted(id)));

        return proposalQuotationMapper.entityToResponse(proposalQuotationItem.getProposalQuotation());

    }
}
