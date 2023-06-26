package org.dargor.product.core.util.mapper;

import org.dargor.product.app.dto.CustomerDto;
import org.dargor.product.app.dto.ProductDto;
import org.dargor.product.app.dto.request.WishListDto;
import org.dargor.product.core.entity.Customer;
import org.dargor.product.core.entity.Product;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.mapstruct.factory.Mappers;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Mapper(imports = {Arrays.class})
public interface ProductMapper {
    ProductMapper INSTANCE = Mappers.getMapper(ProductMapper.class);

    default WishListDto productsToWishListResponse(List<Product> products) {
        var wishList = new WishListDto();
        wishList.setProducts(this.productsToProductDtoList(products));
        wishList.setCustomer(CustomerMapper.INSTANCE.customerToCustomerDto(products.get(0).getCustomer()));
        return wishList;
    }

    default List<Product> wishListRequestToProductList(WishListDto wishList) {
        return wishList.getProducts()
                .stream()
                .map(productDto -> this.productDtoToProduct(productDto, wishList.getCustomer()))
                .collect(Collectors.toList());
    }

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "customer", source = "customerDto", qualifiedByName = "customerDtoToCustomer")
    Product productDtoToProduct(ProductDto productDto, CustomerDto customerDto);

    @Mapping(target = "wishList", ignore = true)
    @Mapping(target = "id", ignore = true)
    @Named("customerDtoToCustomer")
    Customer customerDtoCustomer(CustomerDto customerDto);

    List<ProductDto> productsToProductDtoList(List<Product> products);

    @Mapper
    interface CustomerMapper {
        CustomerMapper INSTANCE = Mappers.getMapper(CustomerMapper.class);

        CustomerDto customerToCustomerDto(Customer customer);

    }

}
