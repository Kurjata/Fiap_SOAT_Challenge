package com.fiap.soat.api.dao.repository;

import com.fiap.soat.entities.document.customer.CustomerDocument;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Mono;

@Repository
public interface CustomerRepository extends ReactiveMongoRepository<CustomerDocument, ObjectId> {
  Mono<Boolean> existsByDocumentNumber(String documentNumber);

  Mono<CustomerDocument> findByDocumentNumber(String documentNumber);
}
