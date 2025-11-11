package com.viniciusdev.project_performance.mappers;

import com.viniciusdev.project_performance.dtos.CustomerRequest;
import com.viniciusdev.project_performance.dtos.CustomerResponse;
import com.viniciusdev.project_performance.entities.Customer;
import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(componentModel = "spring")
public interface CustomerMapper {
    Customer requestToEntity(CustomerRequest customerRequest);
    CustomerResponse entityToResponse(Customer customer);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.SET_TO_NULL)
    void updateEntityFromRequest(@MappingTarget Customer customer, CustomerRequest customerRequest);
}

