package org.dargor.product.app.dto;

import lombok.*;

import java.math.BigDecimal;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class ProductDto {

    private String denomination;
    private String brand;
    private long quantity;
    private BigDecimal unitPrice;

}
