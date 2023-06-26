package org.dargor.product.app.dto.response;

import lombok.*;
import org.dargor.product.app.dto.CustomerDto;
import org.dargor.product.app.dto.ProductDto;

import java.util.List;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class WishListResponse {

    private List<ProductDto> products;
    private CustomerDto customerResponse;

}
