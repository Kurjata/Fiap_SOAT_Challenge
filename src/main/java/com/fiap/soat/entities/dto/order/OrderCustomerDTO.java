package com.fiap.soat.entities.dto.order;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class OrderCustomerDTO {
  private String documentNumber;
  private String name;
  private String email;
}
