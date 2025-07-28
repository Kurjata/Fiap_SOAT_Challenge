package com.fiap.soat.entities.dto.order;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class OrderProductDTO {
    private String id;
    private BigDecimal amount;
    private Integer quantity;
    private String name;
    private String description;

    public BigDecimal getTotalAmount(){
        return amount.multiply(BigDecimal.valueOf(quantity));
    }
}
