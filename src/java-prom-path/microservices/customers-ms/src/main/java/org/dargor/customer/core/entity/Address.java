package org.dargor.customer.core.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.io.Serializable;
import java.util.UUID;

@Getter
@Setter
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "address")
public class Address implements Serializable {

    private static final long serialVersionUID = 713685204499355194L;

    @Id
    @GenericGenerator(name = "uuid2", strategy = "uuid2")
    @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "uuid2")
    @Column(name = "id", updatable = false, nullable = false)
    private UUID id;

    private String street;

    private String number;

    private String city;

    @ManyToOne
    @JoinColumn(name = "customer_id")
    private Customer customer;

}
