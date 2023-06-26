package org.dargor.customer.core.util.mapper;

import org.dargor.customer.app.dto.CustomerDto;
import org.dargor.customer.app.dto.ProductDto;
import org.dargor.customer.app.dto.WishListDto;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface WishListMapper {

    WishListMapper INSTANCE = Mappers.getMapper(WishListMapper.class);

    WishListDto toWishListRequest(CustomerDto customer, List<ProductDto> products);

}
