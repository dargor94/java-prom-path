package org.dargor.batch.config;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.dargor.batch.entity.Customer;
import org.dargor.batch.repository.CustomerRepository;
import org.dargor.batch.step.CustomerProcessor;
import org.dargor.batch.step.CustomerProcessorTwo;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.item.data.RepositoryItemWriter;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Data
@Slf4j
@Configuration
@AllArgsConstructor
@EnableBatchProcessing
public class BatchConfig {

    public final JobBuilderFactory jobBuilderFactory;

    public final StepBuilderFactory stepBuilderFactory;

    private final CustomerRepository customerRepository;

    private final FlatFileItemReader<Customer> customerReader;

    @Qualifier(value = "customer-writer")
    private final RepositoryItemWriter<Customer> customerWriter;

    private final CustomerProcessor customerProcessor;

    private final CustomerProcessorTwo customerProcessorTwo;

    @Bean("import-customers")
    public Job runJob() {
        return jobBuilderFactory.get("importCustomers")
                .flow(step1())
                .end()
                .build();
    }

    @Bean("secondJob")
    public Job runJobTwo() {
        return jobBuilderFactory.get("cus")
                .flow(step2())
                .end()
                .build();
    }

    @Bean
    public Step step1() {
        return stepBuilderFactory.get("csv-step")
                .<Customer, Customer>chunk(10)
                .reader(customerReader)
                .processor(customerProcessor)
                .writer(customerWriter)
                .build();
    }

    @Bean
    public Step step2() {
        return stepBuilderFactory.get("csv-step")
                .<Customer, Customer>chunk(10)
                .reader(customerReader)
                .processor(customerProcessorTwo)
                .writer(customerWriter)
                .build();
    }

    @Bean
    public RepositoryItemWriter<Customer> writer() {
        RepositoryItemWriter<Customer> writer = new RepositoryItemWriter<>();
        writer.setRepository(customerRepository);
        writer.setMethodName("save");
        return writer;
    }

}
