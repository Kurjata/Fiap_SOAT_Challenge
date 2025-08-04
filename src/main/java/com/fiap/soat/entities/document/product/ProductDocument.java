package com.fiap.soat.entities.document.product;

import com.fiap.soat.entities.enums.ProductCategory;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

@Data
@Builder
@Document(collection = "produto")
@NoArgsConstructor
@AllArgsConstructor
public class ProductDocument {
  @Id private ObjectId id;

  @Field(name = "dataHoraCriacao")
  @Builder.Default
  private LocalDateTime timestampCreatedDate = LocalDateTime.now();

  @Field(name = "categoria")
  private ProductCategory category;

  @Field(name = "amount")
  private BigDecimal amount;

  @Field(name = "nome")
  private String name;

  @Field(name = "descricao")
  private String description;
}
