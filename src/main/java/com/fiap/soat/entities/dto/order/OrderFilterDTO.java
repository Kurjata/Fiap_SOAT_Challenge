package com.fiap.soat.entities.dto.order;

import com.fiap.soat.entities.dto.FilterDTO;
import com.fiap.soat.entities.enums.OrderStatus;
import java.time.LocalDateTime;
import java.util.Objects;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.mongodb.core.query.Criteria;

@Data
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class OrderFilterDTO extends FilterDTO {

  private String documentNumber;

  private LocalDateTime startDate;

  private LocalDateTime finalDate;

  private OrderStatus status;

  @Override
  protected Criteria getCriteria() {
    var criteria = new Criteria();

    if (StringUtils.isNotBlank(documentNumber))
      criteria.and("customer.documentNumber").is(documentNumber);

    if (Objects.nonNull(status)) criteria.and("status").is(status.name());

    this.filterLocalDateTime(criteria, "timestampCreatedDate", startDate, finalDate);

    return criteria;
  }
}
