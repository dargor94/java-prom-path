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

@FeignClient(name = "ProductClient", url = "http://localhost:9001/product")
public interface ProductClient {

    @GetMapping("/wish-list/{customerId}")
    List<ProductDto> getWishList(@PathVariable UUID customerId);


    @PostMapping("/create")
    WishListDto createProducts(@RequestBody WishListDto products);

}
