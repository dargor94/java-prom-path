package org.dargor.batch.reader;

import org.dargor.batch.entity.Customer;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.LineMapper;
import org.springframework.batch.item.file.mapping.BeanWrapperFieldSetMapper;
import org.springframework.batch.item.file.mapping.DefaultLineMapper;
import org.springframework.batch.item.file.transform.DelimitedLineTokenizer;
import org.springframework.context.annotation.Bean;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Component;

@Component
public class CustomerCsvReader {

    @Bean("customer-csv-reader")
    public FlatFileItemReader<Customer> reader() {
        var itemReader = new FlatFileItemReader<Customer>();
        itemReader.setResource(new ClassPathResource("customers.csv"));
        itemReader.setName("csvReader");
        //No leemos la primera línea, porque son cabeceras
        itemReader.setLinesToSkip(1);
        itemReader.setLineMapper(lineMapper());
        return itemReader;
    }

    private LineMapper<Customer> lineMapper() {
        var lineMapper = new DefaultLineMapper<Customer>();

        var lineTokenizer = new DelimitedLineTokenizer();
        lineTokenizer.setDelimiter(",");
        /*
         * Siendo false, Las lineas con menos campos (tokens) se completan con columnas vacías,
         * las lineas con mas campos se truncan
         */
        lineTokenizer.setStrict(false);
        lineTokenizer.setNames("id", "firstName", "lastName", "email", "country");

        var fieldSetMapper = new BeanWrapperFieldSetMapper<Customer>();
        fieldSetMapper.setTargetType(Customer.class);

        lineMapper.setLineTokenizer(lineTokenizer);
        lineMapper.setFieldSetMapper(fieldSetMapper);
        return lineMapper;

    }

}
