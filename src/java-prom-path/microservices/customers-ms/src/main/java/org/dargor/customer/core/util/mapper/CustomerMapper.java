package org.dargor.customer.core.util.mapper;

import org.dargor.customer.app.dto.CustomerCreationRequestDto;
import org.dargor.customer.app.dto.CustomerDto;
import org.dargor.customer.app.dto.CustomerUpdateRequestDto;
import org.dargor.customer.core.entity.Customer;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface CustomerMapper {

    CustomerMapper INSTANCE = Mappers.getMapper(CustomerMapper.class);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "active", expression = "java(true)")
    Customer customerCreationRequestToCustomer(CustomerCreationRequestDto customerCreationRequest);

    CustomerDto customerToCustomerResponse(Customer customer);

    Customer customerUpdateRequestToCustomer(CustomerUpdateRequestDto customerUpdateRequest);

}
