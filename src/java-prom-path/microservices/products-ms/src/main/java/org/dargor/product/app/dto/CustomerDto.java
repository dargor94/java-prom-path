package org.dargor.product.app.dto;

import lombok.*;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class CustomerDto {

    private String firstName;

    private String lastName;

    private String email;

    private String password;

    private boolean active;

}
