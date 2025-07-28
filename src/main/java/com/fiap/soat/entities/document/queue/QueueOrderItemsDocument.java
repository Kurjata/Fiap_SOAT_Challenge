package com.fiap.soat.entities.document.queue;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Field;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class QueueOrderItemsDocument {
  @Field(name = "quantidade")
  private Integer quantity;

  @Field(name = "nome")
  private String name;
}
