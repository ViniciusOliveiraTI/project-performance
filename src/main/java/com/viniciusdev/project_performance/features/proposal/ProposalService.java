package com.viniciusdev.project_performance.features.proposal;

import com.viniciusdev.project_performance.common.exception.DataIntegrityException;
import com.viniciusdev.project_performance.common.exception.NotFoundException;
import com.viniciusdev.project_performance.features.customer.entities.Customer;
import com.viniciusdev.project_performance.features.proposal.dtos.ProposalRequest;
import com.viniciusdev.project_performance.features.proposal.dtos.ProposalResponse;
import com.viniciusdev.project_performance.features.customer.CustomerRepository;
import com.viniciusdev.project_performance.features.proposal.entities.Proposal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProposalService {

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private ProposalRepository proposalrepository;

    @Autowired
    private ProposalMapper mapper;

    public List<ProposalResponse> findAll() {
        return proposalrepository.findAll()
                .stream()
                .map(mapper::entityToResponse).toList();
    }

    public ProposalResponse findById(Long id) {
        return mapper.entityToResponse(
                proposalrepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Proposal with id '%d' not found".formatted(id))));
    }

    public ProposalResponse create(ProposalRequest proposalRequest) {

        Customer customer = customerRepository
                .findById(proposalRequest.customerId())
                .orElseThrow(() -> new NotFoundException("Customer with id '%d' not found".formatted(proposalRequest.customerId())));

        Proposal proposal = mapper.requestToEntity(proposalRequest);

        proposal.setCustomer(customer);

        Proposal saved = proposalrepository.save(proposal);

        return mapper.entityToResponse(saved);

    }

    public void deleteById(Long id) {
        if (!proposalrepository.existsById(id)) {
            throw new NotFoundException("Proposal with id '%d' not found".formatted(id));
        };
        try {
            proposalrepository.deleteById(id);
        }
        catch (DataIntegrityViolationException e) {
            throw new DataIntegrityException("Proposal with id '%d' cannot be deleted due to relations".formatted(id));
        }
    }

    public ProposalResponse update(ProposalRequest proposalRequest, Long id) {

        Proposal proposal = proposalrepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Proposal with id '%d' not found".formatted(id)));

        mapper.updateEntityFromRequest(proposal, proposalRequest);

        Customer customer = customerRepository.findById(proposalRequest.customerId())
                .orElseThrow(() -> new NotFoundException("Customer with id '%d' not found".formatted(proposalRequest.customerId())));

        proposal.setCustomer(customer);

        return mapper.entityToResponse(proposalrepository.save(proposal));
    }
}
