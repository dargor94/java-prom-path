package org.dargor.product.app.dto.request;

import lombok.*;
import org.dargor.product.app.dto.CustomerDto;
import org.dargor.product.app.dto.ProductDto;

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
