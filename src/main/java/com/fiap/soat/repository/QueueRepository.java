package com.fiap.soat.repository;

import com.fiap.soat.model.document.queue.QueueDocument;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface QueueRepository extends ReactiveMongoRepository<QueueDocument, ObjectId> {}
