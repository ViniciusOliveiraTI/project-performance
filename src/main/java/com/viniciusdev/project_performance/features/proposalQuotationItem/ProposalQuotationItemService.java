package com.viniciusdev.project_performance.features.proposalQuotationItem;

import com.viniciusdev.project_performance.common.exception.DataIntegrityException;
import com.viniciusdev.project_performance.common.exception.NotFoundException;
import com.viniciusdev.project_performance.features.proposalQuotationItem.dtos.ProposalQuotationItemRequest;
import com.viniciusdev.project_performance.features.proposalQuotationItem.dtos.ProposalQuotationItemResponse;
import com.viniciusdev.project_performance.features.proposalQuotation.entities.ProposalQuotation;
import com.viniciusdev.project_performance.features.proposalQuotationItem.entities.ProposalQuotationItem;
import com.viniciusdev.project_performance.features.proposalQuotation.ProposalQuotationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProposalQuotationItemService {

    @Autowired
    private ProposalQuotationRepository proposalQuotationRepository;

    @Autowired
    private ProposalQuotationItemRepository proposalQuotationItemRepository;

    @Autowired
    private ProposalQuotationItemMapper mapper;

    public List<ProposalQuotationItemResponse> findAll() {
        return proposalQuotationItemRepository.findAll()
                .stream()
                .map(mapper::entityToResponse).toList();
    }

    public ProposalQuotationItemResponse findById(Long id) {
        return mapper.entityToResponse(
                proposalQuotationItemRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Proposal Quotation Item with id '%d' not found".formatted(id))));
    }

    public ProposalQuotationItemResponse create(ProposalQuotationItemRequest proposalQuotationItemRequest) {

        ProposalQuotation proposalQuotation = proposalQuotationRepository
                .findById(proposalQuotationItemRequest.proposalQuotationId())
                .orElseThrow(() -> new NotFoundException("Proposal Quotation with id '%d' not found".formatted(proposalQuotationItemRequest.proposalQuotationId())));

        ProposalQuotationItem proposalQuotationItem = mapper.requestToEntity(proposalQuotationItemRequest);

        proposalQuotationItem.setProposalQuotation(proposalQuotation);

        ProposalQuotationItem saved = proposalQuotationItemRepository.save(proposalQuotationItem);

        return mapper.entityToResponse(saved);

    }

    public void deleteById(Long id) {
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

    public ProposalQuotationItemResponse update(ProposalQuotationItemRequest proposalQuotationItemRequest, Long id) {

        ProposalQuotationItem proposalQuotationItem = proposalQuotationItemRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Proposal Quotation Item with id '%d' not found".formatted(id)));

        mapper.updateEntityFromRequest(proposalQuotationItem, proposalQuotationItemRequest);

        ProposalQuotation proposalQuotation = proposalQuotationRepository.findById(proposalQuotationItemRequest.proposalQuotationId())
                .orElseThrow(() -> new NotFoundException("Proposal Quotation with id '%d' not found".formatted(proposalQuotationItemRequest.proposalQuotationId())));

        proposalQuotationItem.setProposalQuotation(proposalQuotation);

        return mapper.entityToResponse(proposalQuotationItemRepository.save(proposalQuotationItem));
    }
}
