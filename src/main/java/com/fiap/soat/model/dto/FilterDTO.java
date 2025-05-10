package com.fiap.soat.model.dto;

import com.fiap.soat.util.SpringContext;
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
    return new Query(getCriteria())
        .with(getPageable())
        .with(Sort.by(Sort.Direction.DESC, "timestampCreatedDate"));
  }

  public Query getCountQuery() {
    return new Query(getCriteria());
  }
}
