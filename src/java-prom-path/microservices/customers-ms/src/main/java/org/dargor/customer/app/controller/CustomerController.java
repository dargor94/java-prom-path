package org.dargor.customer.app.controller;

import lombok.AllArgsConstructor;
import org.dargor.customer.app.dto.CustomerDto;
import org.dargor.customer.app.dto.WishListDto;
import org.dargor.customer.app.dto.CustomerCreationRequestDto;
import org.dargor.customer.app.dto.CustomerUpdateRequestDto;
import org.dargor.customer.app.service.CustomerService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.UUID;

@Validated
@RestController
@AllArgsConstructor
@RequestMapping("/customer")
public class CustomerController {

    private final CustomerService customerService;

    @PostMapping("/create")
    public ResponseEntity<WishListDto> createCustomer(@RequestBody @Valid CustomerCreationRequestDto request) {
        var response = customerService.createCustomer(request);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @GetMapping("/{customerId}")
    public ResponseEntity<CustomerDto> getCustomer(@PathVariable UUID customerId) {
        var response = customerService.getCustomer(customerId);
        return ResponseEntity.ok(response);
    }

    @PutMapping("/update")
    public ResponseEntity<CustomerDto> updateCustomer(@RequestBody @Valid CustomerUpdateRequestDto request) {
        var response = customerService.updateCustomer(request);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/wish-list/{customerId}")
    public ResponseEntity<WishListDto> getWishList(@PathVariable UUID customerId) {
        var response = customerService.getWishList(customerId);
        return ResponseEntity.ok(response);
    }

}
