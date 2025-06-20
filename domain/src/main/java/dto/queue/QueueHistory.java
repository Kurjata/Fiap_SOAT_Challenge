package dto.queue;


import java.time.LocalDateTime;

import enums.QueueTrackingStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class QueueHistory {

  private LocalDateTime timestamp;

  private QueueTrackingStatus status;
}
