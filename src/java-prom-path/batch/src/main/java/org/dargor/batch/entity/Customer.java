package org.dargor.batch.entity;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "CUSTOMERS_INFO")
public class Customer {

    @Id
    @Column(name = "CUSTOMER_ID", nullable = false)
    private int id;
    @Column(name = "FIRST_NAME")
    private String firstName;
    @Column(name = "LAST_NAME")
    private String lastName;
    @Column(name = "EMAIL")
    private String email;
    @Column(name = "COUNTRY")
    private String country;

}
