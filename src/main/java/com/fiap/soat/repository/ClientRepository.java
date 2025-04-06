package com.fiap.soat.repository;

import com.fiap.soat.model.document.ClientDocument;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

public interface ClientRepository extends ReactiveMongoRepository<ClientDocument, ObjectId> {}
