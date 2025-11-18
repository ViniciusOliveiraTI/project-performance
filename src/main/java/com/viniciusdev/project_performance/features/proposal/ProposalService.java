package com.viniciusdev.project_performance.features.proposal;

import com.viniciusdev.project_performance.app.exception.DataIntegrityException;
import com.viniciusdev.project_performance.app.exception.NotFoundException;
import com.viniciusdev.project_performance.features.customer.CustomerMapper;
import com.viniciusdev.project_performance.features.customer.dtos.CustomerResponse;
import com.viniciusdev.project_performance.features.customer.entities.Customer;
import com.viniciusdev.project_performance.features.project.ProjectMapper;
import com.viniciusdev.project_performance.features.project.dtos.ProjectResponse;
import com.viniciusdev.project_performance.features.proposal.dtos.ProposalRequest;
import com.viniciusdev.project_performance.features.proposal.dtos.ProposalResponse;
import com.viniciusdev.project_performance.features.customer.CustomerRepository;
import com.viniciusdev.project_performance.features.proposal.entities.Proposal;
import com.viniciusdev.project_performance.features.proposalQuotation.ProposalQuotationMapper;
import com.viniciusdev.project_performance.features.proposalQuotation.dtos.ProposalQuotationResponse;
import com.viniciusdev.project_performance.features.user.UserRepository;
import com.viniciusdev.project_performance.features.user.entities.User;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class ProposalService {

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private ProposalRepository proposalrepository;

    @Autowired
    private ProposalMapper proposalMapper;

    @Autowired
    private ProjectMapper projectMapper;

    @Autowired
    private CustomerMapper customerMapper;

    @Autowired
    private ProposalQuotationMapper proposalQuotationMapper;

    @Autowired
    private UserRepository userRepository;

    public List<ProposalResponse> findAll() {
        return proposalrepository.findAll()
                .stream()
                .map(proposalMapper::entityToResponse).toList();
    }

    public ProposalResponse findById(UUID id) {
        return proposalMapper.entityToResponse(
                proposalrepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Proposal with id '%s' not found".formatted(id))));
    }

    @Transactional
    public ProposalResponse create(ProposalRequest proposalRequest) {

        Customer customer = customerRepository
                .findById(proposalRequest.customerId())
                .orElseThrow(() -> new NotFoundException("Customer with id '%s' not found".formatted(proposalRequest.customerId())));

        User manager = userRepository
                .findById(proposalRequest.managerId())
                .orElseThrow(() -> new NotFoundException("Manager with id '%s' not found".formatted(proposalRequest.managerId())));

        Proposal proposal = proposalMapper.requestToEntity(proposalRequest);

        proposal.setCustomer(customer);

        proposal.setManager(manager);

        Proposal saved = proposalrepository.save(proposal);

        return proposalMapper.entityToResponse(saved);

    }

    public void deleteById(UUID id) {
        if (!proposalrepository.existsById(id)) {
            throw new NotFoundException("Proposal with id '%s' not found".formatted(id));
        };
        try {
            proposalrepository.deleteById(id);
        }
        catch (DataIntegrityViolationException e) {
            throw new DataIntegrityException("Proposal with id '%s' cannot be deleted due to relations".formatted(id));
        }
    }

    public ProposalResponse update(ProposalRequest proposalRequest, UUID id) {

        Customer customer = customerRepository
                .findById(proposalRequest.customerId())
                .orElseThrow(() -> new NotFoundException("Customer with id '%s' not found".formatted(proposalRequest.customerId())));

        User manager = userRepository
                .findById(proposalRequest.managerId())
                .orElseThrow(() -> new NotFoundException("Manager with id '%s' not found".formatted(proposalRequest.managerId())));

        Proposal proposal = proposalMapper.requestToEntity(proposalRequest);

        proposal.setCustomer(customer);

        proposal.setManager(manager);

        return proposalMapper.entityToResponse(proposalrepository.save(proposal));
    }

    public List<ProjectResponse> findAllProjects(UUID id) {

        Proposal proposal = proposalrepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Proposal with id '%s' not found".formatted(id)));

        return proposal.getProjects()
                .stream()
                .map(projectMapper::entityToResponse)
                .toList();
    }

    public List<ProposalQuotationResponse> findAllProposalQuotation(UUID id) {

        Proposal proposal = proposalrepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Proposal with id '%s' not found".formatted(id)));

        return proposal.getProposalQuotations()
                .stream()
                .map(proposalQuotationMapper::entityToResponse)
                .toList();
    }

    public CustomerResponse findCustomer(UUID id) {

        Proposal proposal = proposalrepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Proposal with id '%s' not found".formatted(id)));

        return customerMapper.entityToResponse(proposal.getCustomer());

    }
}
