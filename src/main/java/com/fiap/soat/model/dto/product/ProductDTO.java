package com.fiap.soat.model.dto.product;

import com.fiap.soat.model.enums.ProductCategory;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ProductDTO {
  private String id;
  private LocalDateTime timestampCreatedDate;
  private ProductCategory type;
  private BigDecimal amount;
  private String name;
  private String description;
  private List<ProductImageDTO> images;
}
