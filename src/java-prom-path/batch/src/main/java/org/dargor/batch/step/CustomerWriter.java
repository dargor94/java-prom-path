package org.dargor.batch.step;

import org.dargor.batch.entity.Customer;
import org.dargor.batch.repository.CustomerRepository;
import org.springframework.batch.item.data.RepositoryItemWriter;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

@Component
public class CustomerWriter {

    @Bean("customer-writer")
    public RepositoryItemWriter<Customer> writer(CustomerRepository customerRepository) {
        RepositoryItemWriter<Customer> writer = new RepositoryItemWriter<>();
        writer.setRepository(customerRepository);
        writer.setMethodName("save");
        return writer;
    }

}
