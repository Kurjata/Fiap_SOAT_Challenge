package com.fiap.soat.model.document.product;

import com.fiap.soat.model.enums.ProductCategory;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Data
@Builder
@Document(collection = "produto")
@NoArgsConstructor
@AllArgsConstructor
public class ProductDocument {
  @Id @Builder.Default private ObjectId id = new ObjectId();

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

  @Field(name = "imagens")
  @Builder.Default
  private List<ProductImageDocument> images = new ArrayList<>();
}
