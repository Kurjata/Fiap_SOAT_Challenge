package com.fiap.outbound.repository;

import com.fiap.document.order.OrderDocument;
import com.fiap.dto.order.OrderFilter;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Repository
public interface MongoOrderRepository extends ReactiveMongoRepository<OrderDocument, ObjectId> {
    default Flux<OrderDocument> getByFilter(OrderFilter filter) {
        return filter.getContext().find(filter.getQuery(), OrderDocument.class);
    }

    default Mono<Long> getCountByFilter(OrderFilter filter) {
        return filter.getContext().count(filter.getCountQuery(), OrderDocument.class);
    }
}
