package com.fiap.soat.model.dto.product;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ProductImageDTO {
  private LocalDateTime timestampCreatedDate;
  private String name;
  private byte[] bytes;
}
