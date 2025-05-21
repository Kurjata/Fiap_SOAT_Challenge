package com.fiap.soat.model.document.queue;

import com.fiap.soat.model.enums.QueueTrackingStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Data
@Builder
@Document(collection = "fila")
@NoArgsConstructor
@AllArgsConstructor
public class QueueDocument {
  @Id private ObjectId id;

  @Field(name = "dataHoraCriacao")
  @Builder.Default
  private LocalDateTime timestampCreatedDate = LocalDateTime.now();

  @Field(name = "pedidoId")
  private String orderId;

  private QueueTrackingStatus status;

  @Field(name = "apelido")
  private String surname;

  @Field(name = "historico")
  @Builder.Default
  private List<QueueHistoryDocument> history = new ArrayList<>();
}
