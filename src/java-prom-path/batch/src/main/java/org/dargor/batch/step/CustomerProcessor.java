package org.dargor.batch.step;

import org.dargor.batch.entity.Customer;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.stereotype.Component;

@Component
public class CustomerProcessor implements ItemProcessor<Customer, Customer> {

    @Override
    public Customer process(Customer customer) {
        if (customer.getCountry().equals("Argentina")) {
            return customer;
        } else {
            return null;
        }
    }

}
