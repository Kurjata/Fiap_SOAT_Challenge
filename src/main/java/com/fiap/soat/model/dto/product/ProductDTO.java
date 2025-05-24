package com.fiap.soat.model.dto.product;

import com.fiap.soat.model.enums.ProductCategory;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ProductDTO {
  private String id;
  private LocalDateTime timestampCreatedDate;
  private ProductCategory category;
  private BigDecimal amount;
  private String name;
  private String description;
}
