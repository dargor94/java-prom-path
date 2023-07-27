package org.dargor.customer.app.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class WishListDto {

    private CustomerDto customer;
    private List<ProductDto> products;

}
