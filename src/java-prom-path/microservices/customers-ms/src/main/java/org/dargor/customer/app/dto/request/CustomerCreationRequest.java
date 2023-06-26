package org.dargor.customer.app.dto.request;

import lombok.*;
import org.dargor.customer.app.dto.AddressDto;
import org.dargor.customer.app.dto.ProductDto;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import java.util.List;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class CustomerCreationRequest {

    @NotEmpty
    private String firstName;

    @NotEmpty
    private String lastName;

    @Email
    @NotEmpty
    private String email;

    @NotEmpty
    private String password;

    private List<AddressDto> addresses;

    private List<ProductDto> products;

}
