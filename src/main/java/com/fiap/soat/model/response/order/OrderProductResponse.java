package com.fiap.soat.model.response.order;

import java.math.BigDecimal;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class OrderProductResponse {
    private String id;
    private BigDecimal amount;
    private String name;
    private String description;
}
