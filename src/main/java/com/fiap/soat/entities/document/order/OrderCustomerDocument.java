package com.fiap.soat.entities.document.order;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Field;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class OrderCustomerDocument {
  @Field(name = "cpf")
  private String documentNumber;

  @Field(name = "nome")
  private String name;

  private String email;
}
