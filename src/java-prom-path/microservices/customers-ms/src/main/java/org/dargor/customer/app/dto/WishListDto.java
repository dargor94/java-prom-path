package org.dargor.customer.app.dto;

import lombok.*;

import java.util.List;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class WishListDto {

    private CustomerDto customer;
    private List<ProductDto> products;

}
