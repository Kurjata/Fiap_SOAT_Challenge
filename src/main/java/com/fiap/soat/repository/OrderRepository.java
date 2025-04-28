package com.fiap.soat.repository;

import com.fiap.soat.model.document.order.OrderDocument;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderRepository extends ReactiveMongoRepository<OrderDocument, ObjectId> {}
