package com.fiap.soat.entities.dto.queue;


import java.time.LocalDateTime;

import com.fiap.soat.entities.enums.QueueTrackingStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class QueueHistoryDTO {

  private LocalDateTime timestamp;

  private QueueTrackingStatus status;
}
