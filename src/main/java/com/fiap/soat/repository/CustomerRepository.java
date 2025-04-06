package com.fiap.soat.repository;

import com.fiap.soat.model.document.customer.CustomerDocument;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

public interface CustomerRepository extends ReactiveMongoRepository<CustomerDocument, ObjectId> {}
