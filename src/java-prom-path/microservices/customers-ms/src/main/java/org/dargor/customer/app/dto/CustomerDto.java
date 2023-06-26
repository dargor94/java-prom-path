package org.dargor.customer.app.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import java.util.List;
import java.util.UUID;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class CustomerDto {

    private UUID id;

    private String firstName;

    private String lastName;

    @JsonProperty("user")
    private String email;

    private boolean active;

    private List<AddressDto> addresses;


}
