package com.fiap.soat.entities.dto.queue;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import com.fiap.soat.entities.enums.QueueTrackingStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class QueueDTO {
  private String id;

  private String orderId;

  private QueueTrackingStatus status;

  private LocalDateTime timestampCurrentStatus;

  private String nickname;

  @Builder.Default private List<QueueHistoryDTO> history = new ArrayList<>();

  @Builder.Default private List<QueueOrderItemsDTO> items = new ArrayList<>();
}
