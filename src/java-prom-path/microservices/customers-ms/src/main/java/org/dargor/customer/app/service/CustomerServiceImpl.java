package org.dargor.customer.app.service;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.dargor.customer.app.client.ProductClient;
import org.dargor.customer.app.dto.CustomerDto;
import org.dargor.customer.app.dto.WishListDto;
import org.dargor.customer.app.dto.request.CustomerCreationRequest;
import org.dargor.customer.app.dto.request.CustomerUpdateRequest;
import org.dargor.customer.core.repository.CustomerRepository;
import org.dargor.customer.core.util.mapper.CustomerMapper;
import org.dargor.customer.core.util.mapper.ProductMapper;
import org.dargor.customer.core.util.mapper.WishListMapper;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Slf4j
@Service
@AllArgsConstructor
public class CustomerServiceImpl implements CustomerService {

    private final CustomerRepository customerRepository;

    private final ProductClient productClient;
    private final CustomerMapper customerMapper = CustomerMapper.INSTANCE;
    private final ProductMapper productMapper = ProductMapper.INSTANCE;
    private final WishListMapper wishListMapper = WishListMapper.INSTANCE;

    @Override
    public WishListDto createCustomer(CustomerCreationRequest request) {
        try {
            var customer = customerMapper.customerCreationRequestToCustomer(request);
            var savedCustomer = customerRepository.save(customer);
            var customerResponse = customerMapper.customerToCustomerResponse(savedCustomer);
            var wishListRequest = wishListMapper.toWishListRequest(customerResponse, request.getProducts());
            var response = productClient.createProducts(wishListRequest);
            log.info(String.format("Customer created successfully [request %s] [response: %s]", request, response.toString()));
            return response;
        } catch (Exception e) {
            log.error(String.format("Error found creating customer [request %s]", request.toString()));
            throw e;
        }
    }

    @Override
    public CustomerDto getCustomer(UUID customerId) {
        try {
            var customer = customerRepository.getById(customerId);
            var response = customerMapper.customerToCustomerResponse(customer);

            log.info(String.format("Customer updated successfully [customerId %s] [response: %s]", customerId, response.toString()));
            return response;
        } catch (Exception e) {
            log.error(String.format("Error found updating customer [customerId %s]", customerId));
            throw e;
        }
    }

    @Override
    public CustomerDto updateCustomer(CustomerUpdateRequest request) {
        try {
            var customer = customerMapper.customerUpdateRequestToCustomer(request);
            var updatedCustomer = customerRepository.save(customer);
            var response = customerMapper.customerToCustomerResponse(updatedCustomer);

            log.info(String.format("Customer updated successfully [request %s] [response: %s]", request.toString(), response.toString()));
            return response;
        } catch (Exception e) {
            log.error(String.format("Error found updating customer [request %s]", request.toString()));
            throw e;
        }
    }

    public WishListDto getWishList(UUID customerId) {
        try {
            var wishList = productClient.getWishList(customerId);
            log.info(String.format("Products fetched successfully [products %s]", wishList.toString()));

            var customer = customerRepository.getById(customerId);
            log.info(String.format("Customer fetched successfully [entity %s]", customer));

            var response = productMapper.toWishListDto(customer, wishList);
            log.info(String.format("Request performed successfully [request %s] [response: %s]", customerId, response.toString()));

            return response;
        } catch (Exception e) {
            log.error(String.format("Error found adding products to cart for customer [id %s]", customerId.toString()));
            throw e;
        }
    }

}
