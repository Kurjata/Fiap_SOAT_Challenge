package com.fiap.soat.entities.document.queue;


import java.time.LocalDateTime;

import com.fiap.soat.entities.enums.QueueTrackingStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Field;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class QueueHistoryDocument {
  @Field(name = "dataHora")
  private LocalDateTime timestamp;

  private QueueTrackingStatus status;
}
