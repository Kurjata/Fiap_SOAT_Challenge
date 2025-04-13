package com.fiap.soat.model.document.product;

import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.core.mapping.Field;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ProductImageDocument {
  
  @Builder.Default
  private String id = new ObjectId().toHexString();
  
  @Field(name = "dataHoraCriacao")
  @Builder.Default
  private LocalDateTime timestampCreatedDate = LocalDateTime.now();

  @Field(name = "nome")
  private String name;

  private byte[] bytes;
}
