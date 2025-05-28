package com.fiap.soat.model.dto.queue;

import com.fiap.soat.model.enums.QueueTrackingStatus;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
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
