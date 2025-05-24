package com.fiap.soat.model.dto.queue;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class QueueOrderItemsDTO {
  private Integer quantity;
  private String name;
}
