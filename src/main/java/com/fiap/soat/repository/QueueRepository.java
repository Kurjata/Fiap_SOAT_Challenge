package com.fiap.soat.repository;

import com.fiap.soat.model.document.queue.QueueDocument;
import com.fiap.soat.model.dto.queue.QueueFilterDTO;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Repository
public interface QueueRepository extends ReactiveMongoRepository<QueueDocument, ObjectId> {
  default Flux<QueueDocument> getByFilter(QueueFilterDTO filter) {
    return filter.getContext().find(filter.getQuery("timestampCurrentStatus"), QueueDocument.class);
  }

  default Mono<Long> getCountByFilter(QueueFilterDTO filter) {
    return filter.getContext().count(filter.getCountQuery(), QueueDocument.class);
  }
}
