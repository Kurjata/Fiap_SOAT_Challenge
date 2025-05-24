package com.fiap.soat.model.response.queue;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class QueueOrderItemsResponse {
  private String name;
  private Integer quantity;
}
