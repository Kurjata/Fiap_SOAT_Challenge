package com.fiap.soat.model.dto.queue;

import com.fiap.soat.model.dto.FilterDTO;
import com.fiap.soat.model.enums.QueueTrackingStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import org.springframework.data.mongodb.core.query.Criteria;

import java.time.LocalDateTime;
import java.util.Objects;

@Data
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class QueueFilterDTO extends FilterDTO {

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
