package com.fiap.soat.model.document.queue;

import com.fiap.soat.model.enums.QueueTrackingStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Field;

import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class QueueHistoryDocument {
  @Field(name = "dataHora")
  private LocalDateTime timestamp;

  private QueueTrackingStatus status;
}
