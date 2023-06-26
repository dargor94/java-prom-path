package org.dargor.customer.app.dto;

import lombok.*;
import org.dargor.customer.app.dto.AddressDto;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import java.util.List;
import java.util.UUID;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class CustomerUpdateRequestDto {

    @NotEmpty
    private UUID id;

    private String firstName;

    private String lastName;

    @Email
    private String email;

    private String password;

    private boolean active;

    private List<AddressDto> addresses;

}
