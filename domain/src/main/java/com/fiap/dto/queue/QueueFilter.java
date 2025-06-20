package com.fiap.dto.queue;

import com.fiap.dto.Filter;
import com.fiap.enums.QueueTrackingStatus;
import java.time.LocalDateTime;
import java.util.Objects;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import org.springframework.data.mongodb.core.query.Criteria;

@Data
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class QueueFilter extends Filter {

  private LocalDateTime startDate;

  private LocalDateTime finalDate;

  private QueueTrackingStatus status;

  @Override
  protected Criteria getCriteria() {
    var criteria = new Criteria();

    if (Objects.nonNull(status)) criteria.and("status").is(status.name());

    this.filterLocalDateTime(criteria, "timestampCurrentStatus", startDate, finalDate);

    return criteria;
  }
}
