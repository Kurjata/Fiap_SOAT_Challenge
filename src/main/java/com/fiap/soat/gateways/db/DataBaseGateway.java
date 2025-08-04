package com.fiap.soat.gateways.db;

import org.bson.types.ObjectId;
import reactor.core.publisher.Mono;

public interface DataBaseGateway<T> {
  Mono<T> findById(String id);

  Mono<T> save(T obj);

  default Mono<ObjectId> stringToId(String id) {
    return Mono.just(id).filter(ObjectId::isValid).map(ObjectId::new);
  }
}
