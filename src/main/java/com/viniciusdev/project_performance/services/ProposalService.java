package com.viniciusdev.project_performance.services;

import com.viniciusdev.project_performance.dtos.ProposalRequest;
import com.viniciusdev.project_performance.dtos.ProposalResponse;
import com.viniciusdev.project_performance.entities.Customer;
import com.viniciusdev.project_performance.entities.Proposal;
import com.viniciusdev.project_performance.mappers.ProposalMapper;
import com.viniciusdev.project_performance.repositories.CustomerRepository;
import com.viniciusdev.project_performance.repositories.ProposalRepository;
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
                .orElseThrow(RuntimeException::new));
    }

    public ProposalResponse create(ProposalRequest proposalRequest) {

        Customer customer = customerRepository
                .findById(proposalRequest.customerId())
                .orElseThrow(RuntimeException::new);

        Proposal proposal = mapper.requestToEntity(proposalRequest);

        proposal.setCustomer(customer);

        Proposal saved = proposalrepository.save(proposal);

        return mapper.entityToResponse(saved);

    }

    public void deleteById(Long id) {
        if (!proposalrepository.existsById(id)) {
            throw new RuntimeException("Proposal %d not found".formatted(id));
        };
        try {
            proposalrepository.deleteById(id);
        }
        catch (DataIntegrityViolationException e) {
            throw new RuntimeException("Proposal %d cannot be deleted due to relations".formatted(id));
        }
    }

    public ProposalResponse update(ProposalRequest proposalRequest, Long id) {

        Proposal proposal = proposalrepository.findById(id)
                .orElseThrow(RuntimeException::new);

        mapper.updateEntityFromRequest(proposal, proposalRequest);

        Customer customer = customerRepository.findById(proposalRequest.customerId())
                .orElseThrow(RuntimeException::new);

        proposal.setCustomer(customer);

        return mapper.entityToResponse(proposalrepository.save(proposal));
    }
}
