package org.dargor.batch.reader;

import lombok.AllArgsConstructor;
import org.dargor.batch.entity.Customer;
import org.dargor.batch.repository.CustomerRepository;
import org.springframework.batch.item.data.RepositoryItemReader;
import org.springframework.batch.item.data.builder.RepositoryItemReaderBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
@AllArgsConstructor
public class CustomerJpaReader {

    @Bean("customer-jpa-reader")
    public RepositoryItemReader<Customer> repositoryItemWriter(CustomerRepository customerRepository) {
        return new RepositoryItemReaderBuilder<Customer>()
                .sorts(Map.of("id", Sort.Direction.ASC))
                .repository(customerRepository)
                .methodName("findAll")
                .saveState(false)
                .build();
    }

}
