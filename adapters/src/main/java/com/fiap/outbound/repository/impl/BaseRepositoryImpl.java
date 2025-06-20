package com.fiap.outbound.repository.impl;

import org.bson.types.ObjectId;
import reactor.core.publisher.Mono;

public abstract class BaseRepositoryImpl {
  Mono<String> validateId(String id) {
    return Mono.just(id).filter(ObjectId::isValid);
  }

  Mono<ObjectId> validateAndCreateMongoId(String id) {
    return validateId(id).map(ObjectId::new);
  }
}
