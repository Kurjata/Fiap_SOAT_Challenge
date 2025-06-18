package dto;

import static org.springframework.data.domain.Sort.Direction.DESC;

import com.fiap.soat.util.SpringContext;
import java.time.LocalDateTime;
import java.util.Objects;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.ReactiveMongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;

@Data
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
public abstract class FilterDTO {
  private Integer page;
  private Integer size;

  protected abstract Criteria getCriteria();

  public ReactiveMongoTemplate getContext() {
      return SpringContext.getBean();
  }

  public Pageable getPageable() {
    return PageRequest.of(this.page, this.size);
  }

  public Query getQuery() {
    return getQuery("timestampCreatedDate", DESC);
  }
  
  public Query getQuery(String orderField) {
    return getQuery(orderField, DESC);
  }

  public Query getQuery(String orderField, Sort.Direction sort) {
    return new Query(getCriteria())
        .with(getPageable())
        .with(Sort.by(sort, orderField));
  }

  public Query getCountQuery() {
    return new Query(getCriteria());
  }

  protected void filterLocalDateTime(Criteria criteria, String field, LocalDateTime start, LocalDateTime end) {
    if (Objects.nonNull(start) && Objects.nonNull(end))
      criteria.and(field).gte(start).lte(end);
    else {
      if (Objects.nonNull(start)) criteria.and(field).gte(start);
      if (Objects.nonNull(end)) criteria.and(field).lte(end);
    }
  }
}
