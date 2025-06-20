package dto.queue;

import com.fiap.soat.model.enums.QueueTrackingStatus;
import java.time.LocalDateTime;
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
