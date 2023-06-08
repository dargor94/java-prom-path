package org.dargor.batch.processor;

import org.dargor.batch.entity.Customer;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.stereotype.Component;

@Component
public class CustomerProcessorTwo implements ItemProcessor<Customer, Customer> {
    @Override
    public Customer process(Customer customer) {
        return customer.getCountry().equals("France") ? customer : null;
    }

}
