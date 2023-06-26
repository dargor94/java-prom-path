package org.dargor.customer.core.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;
import java.util.UUID;

@Getter
@Setter
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "customer")
public class Customer implements Serializable {

    private static final long serialVersionUID = -6238211744789787258L;

    @Id
    @GenericGenerator(name = "uuid2", strategy = "uuid2")
    @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "uuid2")
    @Column(name = "id", updatable = false, nullable = false)
    private UUID id;

    private String firstName;

    private String lastName;

    private String email;

    private String password;

    private boolean active;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "customer")
    private List<Address> addresses;

}
