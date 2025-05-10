package com.fiap.soat.repository;

import com.fiap.soat.model.document.order.OrderDocument;
import com.fiap.soat.model.dto.order.OrderFilterDTO;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Repository
public interface OrderRepository extends ReactiveMongoRepository<OrderDocument, ObjectId> {
    default Flux<OrderDocument> getByFilter(OrderFilterDTO filter) {
        return filter.getContext().find(filter.getQuery(), OrderDocument.class);
    }

    default Mono<Long> getCountByFilter(OrderFilterDTO filter) {
        return filter.getContext().count(filter.getCountQuery(), OrderDocument.class);
    }
}
