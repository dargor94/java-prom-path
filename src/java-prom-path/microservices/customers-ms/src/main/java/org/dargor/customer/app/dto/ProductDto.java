package org.dargor.customer.app.dto;

import lombok.*;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProductDto {

    private String denomination;
    private String brand;
    private long quantity;
    private BigDecimal unitPrice;

}
