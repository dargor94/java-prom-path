package org.dargor.customer.app.service;

import org.dargor.customer.app.dto.CustomerDto;
import org.dargor.customer.app.dto.WishListDto;
import org.dargor.customer.app.dto.request.CustomerCreationRequest;
import org.dargor.customer.app.dto.request.CustomerUpdateRequest;

import java.util.UUID;

public interface CustomerService {


    CustomerDto updateCustomer(CustomerUpdateRequest customerCreationRequest);

    CustomerDto getCustomer(UUID customerId);

    WishListDto createCustomer(CustomerCreationRequest customerCreationRequest);

    WishListDto getWishList(UUID customerId);

}
