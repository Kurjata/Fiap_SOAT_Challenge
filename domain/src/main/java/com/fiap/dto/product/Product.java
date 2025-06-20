package com.fiap.dto.product;

import com.fiap.enums.ProductCategory;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Product {
  private String id;
  private LocalDateTime timestampCreatedDate;
  private ProductCategory category;
  private BigDecimal amount;
  private String name;
  private String description;
}
