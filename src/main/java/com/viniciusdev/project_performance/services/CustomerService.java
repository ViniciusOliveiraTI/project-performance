package com.viniciusdev.project_performance.services;

import com.viniciusdev.project_performance.dtos.CustomerRequest;
import com.viniciusdev.project_performance.dtos.CustomerResponse;
import com.viniciusdev.project_performance.entities.Customer;
import com.viniciusdev.project_performance.mappers.CustomerMapper;
import com.viniciusdev.project_performance.repositories.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomerService {

    @Autowired
    private CustomerRepository repository;

    @Autowired
    private CustomerMapper mapper;

    public List<CustomerResponse> findAll() {
        return repository.findAll()
                .stream()
                .map(mapper::entityToResponse).toList();
    }

    public CustomerResponse findById(Long id) {
        return mapper.entityToResponse(
                repository.findById(id)
                .orElseThrow(RuntimeException::new));
    }

    public CustomerResponse create(CustomerRequest customerRequest) {

        Customer customer = mapper.requestToEntity(customerRequest);

        repository.save(customer);

        return mapper.entityToResponse(customer);

    }

    public void deleteById(Long id) {
        if (!repository.existsById(id)) {
            throw new RuntimeException("Customer %d not found".formatted(id));
        };
        try {
            repository.deleteById(id);
        }
        catch (DataIntegrityViolationException e) {
            throw new RuntimeException("Customer %d cannot be deleted due to relations".formatted(id));
        }
    }

    public CustomerResponse update(CustomerRequest customerRequest, Long id) {

        Customer customer = repository.findById(id)
                .orElseThrow(RuntimeException::new);

        mapper.updateEntityFromRequest(customer, customerRequest);

        return mapper.entityToResponse(repository.save(customer));
    }
}
