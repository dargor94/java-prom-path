package org.dargor.customer.app.client;

import org.dargor.customer.app.dto.ProductDto;
import org.dargor.customer.app.dto.WishListDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;
import java.util.UUID;

@FeignClient(name = "ProductClient",
        url = "${feign.product-ms.host}:${feign.product-ms.port}/${feign.product-ms.id}/${feign.product-ms.url}"
)
public interface ProductClient {

    @GetMapping("/${feign.product-ms.wishlist-url}/{customerId}")
    List<ProductDto> getWishList(@PathVariable UUID customerId);

    @PostMapping("/${feign.product-ms.create-url}")
    WishListDto createProducts(@RequestBody WishListDto products);

}
