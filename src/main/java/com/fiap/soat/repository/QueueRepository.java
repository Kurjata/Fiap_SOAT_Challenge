package com.fiap.soat.repository;

import com.fiap.soat.model.document.queue.QueueDocument;
import com.fiap.soat.model.dto.queue.QueueFilterDTO;
import com.fiap.soat.model.enums.QueueTrackingStatus;
import com.fiap.soat.util.SpringContext;
import org.bson.types.ObjectId;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Repository
public interface QueueRepository extends ReactiveMongoRepository<QueueDocument, ObjectId> {
  default Flux<QueueDocument> getByFilter(QueueFilterDTO filter) {
    return filter.getContext().find(filter.getQuery("timestampCurrentStatus", Sort.Direction.ASC), QueueDocument.class);
  }

  default Mono<Long> getCountByFilter(QueueFilterDTO filter) {
    return filter.getContext().count(filter.getCountQuery(), QueueDocument.class);
  }

  default Mono<QueueDocument> getFirstReceived() {
    var criteria = Criteria.where("status").is(QueueTrackingStatus.RECEIVED.name());
    var query = new Query(criteria).with(Sort.by(Sort.Direction.ASC, "timestampCurrentStatus"));
    return SpringContext.getBean().findOne(query, QueueDocument.class);
  }
}
