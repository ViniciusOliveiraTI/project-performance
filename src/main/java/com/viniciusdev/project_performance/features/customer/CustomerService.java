package com.viniciusdev.project_performance.features.customer;

import com.viniciusdev.project_performance.common.exception.DataIntegrityException;
import com.viniciusdev.project_performance.common.exception.NotFoundException;
import com.viniciusdev.project_performance.features.customer.dtos.CustomerRequest;
import com.viniciusdev.project_performance.features.customer.dtos.CustomerResponse;
import com.viniciusdev.project_performance.features.customer.entities.Customer;
import com.viniciusdev.project_performance.features.proposal.ProposalMapper;
import com.viniciusdev.project_performance.features.proposal.ProposalRepository;
import com.viniciusdev.project_performance.features.proposal.dtos.ProposalResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class CustomerService {

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private ProposalRepository proposalRepository;

    @Autowired
    private CustomerMapper customerMapper;

    @Autowired
    private ProposalMapper proposalMapper;

    public List<CustomerResponse> findAll() {
        return customerRepository.findAll()
                .stream()
                .map(customerMapper::entityToResponse).toList();
    }

    public CustomerResponse findById(UUID id) {
        return customerMapper.entityToResponse(
                customerRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Customer with id '%s' not found".formatted(id.toString()))));
    }

    public CustomerResponse create(CustomerRequest customerRequest) {

        Customer customer = customerMapper.requestToEntity(customerRequest);

        customerRepository.save(customer);

        return customerMapper.entityToResponse(customer);

    }

    public void deleteById(UUID id) {
        if (!customerRepository.existsById(id)) {
            throw new NotFoundException("Customer with id '%s' not found".formatted(id.toString()));
        };
        try {
            customerRepository.deleteById(id);
        }
        catch (DataIntegrityViolationException e) {
            throw new DataIntegrityException("Customer with id '%s' cannot be deleted due to relations".formatted(id.toString()));
        }
    }

    public CustomerResponse update(CustomerRequest customerRequest, UUID id) {

        Customer customer = customerRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Customer with id '%s' not found".formatted(id.toString())));

        customerMapper.updateEntityFromRequest(customer, customerRequest);

        return customerMapper.entityToResponse(customerRepository.save(customer));
    }

    public List<ProposalResponse> findAllProposals(UUID id) {

        Customer customer = customerRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Customer with id '%s' not found".formatted(id.toString())));

        return customer.getProposals()
                .stream()
                .map(proposalMapper::entityToResponse)
                .toList();
    }
}
