package org.dargor.customer.core.util.mapper;

import org.dargor.customer.app.dto.WishListDto;
import org.dargor.customer.app.dto.response.ProductDto;
import org.dargor.customer.core.entity.Customer;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface ProductMapper {
    ProductMapper INSTANCE = Mappers.getMapper(ProductMapper.class);

    @Mapping(target = "products", source = "products")
    @Mapping(target = "customer", source = "customer")
    WishListDto toWishListDto(Customer customer, List<ProductDto> products);

}
