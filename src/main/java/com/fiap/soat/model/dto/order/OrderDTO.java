package com.fiap.soat.model.dto.order;

import com.fiap.soat.model.enums.OrderStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class OrderDTO {
    private String id;
    private LocalDateTime timestampCreatedDate;
    private OrderCustomerDTO customer;
    private BigDecimal totalAmount;
    private OrderStatus status;

    @Builder.Default
    private List<OrderProductDTO> products = new ArrayList<>();
}
