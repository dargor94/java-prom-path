package org.dargor.batch.config;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.dargor.batch.entity.Customer;
import org.dargor.batch.listener.JpaReaderListener;
import org.dargor.batch.processor.CustomerProcessor;
import org.dargor.batch.processor.CustomerProcessorTwo;
import org.dargor.batch.repository.CustomerRepository;
import org.springframework.batch.core.ExitStatus;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.annotation.AfterJob;
import org.springframework.batch.core.annotation.BeforeJob;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.item.data.RepositoryItemReader;
import org.springframework.batch.item.data.RepositoryItemWriter;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.support.ListItemWriter;
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

    @Qualifier("customer-csv-reader")
    private final FlatFileItemReader<Customer> customerCsvReader;

    @Qualifier(value = "customer-jpa-reader")
    private final RepositoryItemReader<Customer> customerJpaReader;

    @Qualifier(value = "customer-csv-writer")
    private final RepositoryItemWriter<Customer> customerRepositoryWriter;

    private final CustomerProcessor customerProcessor;

    private final CustomerProcessorTwo customerProcessorTwo;

    @Bean("import-customers")
    public Job runJob() {
        return jobBuilderFactory.get("importCustomers")
                .flow(step1())
                .next(jpaReaderStep())
                .end()
                .build();
    }

    @Bean("import-customers-all")
    public Job runJobAll() {
        return jobBuilderFactory.get("importCustomersAll")
                .flow(step1All())
                .next(jpaReaderStep())
                .end()
                .build();
    }

    @Bean("secondJob")
    public Job runJobTwo() {
        return jobBuilderFactory.get("two")
                .flow(step2())
                .end()
                .build();
    }

    @Bean
    public Step step1() {
        return stepBuilderFactory.get("csv-step")
                .<Customer, Customer>chunk(10)
                .reader(customerCsvReader)
                .processor(customerProcessor)
                .writer(customerRepositoryWriter)
                .build();
    }

    @Bean
    public Step step1All() {
        return stepBuilderFactory.get("csv-step")
                .<Customer, Customer>chunk(10)
                .reader(customerCsvReader)
                .writer(customerRepositoryWriter)
                .build();
    }

    @Bean
    public Step step2() {
        return stepBuilderFactory.get("csv-step")
                .<Customer, Customer>chunk(10)
                .reader(customerCsvReader)
                .processor(customerProcessorTwo)
                .writer(customerRepositoryWriter)
                .build();
    }

    @Bean
    public Step jpaReaderStep() {
        return stepBuilderFactory.get("jpa-step")
                .<Customer, Customer>chunk(3)
                .listener(jpaReaderListener())
                .reader(customerJpaReader)
                .writer(new ListItemWriter<>())
                .build();
    }

    @Bean
    public JpaReaderListener jpaReaderListener() {
        return new JpaReaderListener();
    }

    @BeforeJob
    public void beforeJob(JobExecution jobExecution) {
        log.info("BEFORE JOBS");
        log.info(jobExecution.getJobConfigurationName());
    }

    @AfterJob
    public void afterJob(JobExecution jobExecution) {
        log.info("AFTER JOBS");
        if (jobExecution.getExitStatus().getExitCode().equals(ExitStatus.FAILED.getExitCode())) {
            jobExecution.getAllFailureExceptions()
                    .forEach(throwable -> log.info(throwable.getMessage()));
        }
    }

}
