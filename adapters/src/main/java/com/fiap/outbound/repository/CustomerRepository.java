package com.fiap.outbound.repository;

import com.fiap.outbound.model.customer.CustomerDocument;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Mono;

@Repository
public interface CustomerRepository extends ReactiveMongoRepository<CustomerDocument, ObjectId> {
  Mono<Boolean> existsByDocumentNumber(String documentNumber);

  Mono<CustomerDocument> findByDocumentNumber(String documentNumber);
}
