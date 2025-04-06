package com.fiap.soat.model.document;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.time.LocalDateTime;

@Data
@Builder
@Document(collation = "cliente")
@NoArgsConstructor
@AllArgsConstructor
public class ClientDocument {
  @Id @Builder.Default private ObjectId id = new ObjectId();

  @Field(name = "dataHoraCriacao")
  @Builder.Default
  private LocalDateTime timestampCreateDate = LocalDateTime.now();

  @Field(name = "cpf")
  private String documentNumber;

  @Field(name = "nome")
  private String name;
}
