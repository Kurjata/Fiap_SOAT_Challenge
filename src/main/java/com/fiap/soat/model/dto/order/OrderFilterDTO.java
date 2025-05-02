package com.fiap.soat.model.dto.order;

import com.fiap.soat.model.dto.FilterDTO;
import com.fiap.soat.model.enums.OrderStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.mongodb.core.query.Criteria;

import java.time.LocalDateTime;
import java.util.Objects;

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

    if (Objects.nonNull(startDate) && Objects.nonNull(finalDate))
      criteria.and("timestampCreatedDate").gte(startDate).lte(finalDate);
    else {
      if (Objects.nonNull(startDate)) criteria.and("timestampCreatedDate").gte(startDate);
      if (Objects.nonNull(finalDate)) criteria.and("timestampCreatedDate").lte(finalDate);
    }

    return criteria;
  }
}
