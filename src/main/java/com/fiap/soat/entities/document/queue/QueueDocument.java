package com.fiap.soat.entities.document.queue;

import static com.fiap.soat.entities.enums.QueueTrackingStatus.RECEIVED;

import com.fiap.soat.entities.enums.QueueTrackingStatus;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.ArrayList;
import java.util.List;
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
@Document(collection = "fila")
@NoArgsConstructor
@AllArgsConstructor
public class QueueDocument {
  @Id private ObjectId id;

  @Field(name = "pedidoId")
  private String orderId;

  @Builder.Default private QueueTrackingStatus status = RECEIVED;

  @Field(name = "dataHoraStatusAtual")
  @Builder.Default
  private LocalDateTime timestampCurrentStatus = LocalDateTime.now();

  @Field(name = "apelido")
  @Builder.Default
  private String nickname = String.valueOf(LocalDateTime.now().toEpochSecond(ZoneOffset.UTC));

  private List<QueueOrderItemsDocument> items;

  @Field(name = "historico")
  @Builder.Default
  private List<QueueHistoryDocument> history = new ArrayList<>();
}
