package org.dargor.customer.app.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import java.util.List;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_EMPTY)
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
