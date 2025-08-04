package com.fiap.soat.api.dao.repository;



import com.fiap.soat.entities.document.product.ProductDocument;
import com.fiap.soat.entities.enums.ProductCategory;
import org.bson.types.ObjectId;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Repository
public interface ProductRepository extends ReactiveMongoRepository<ProductDocument, ObjectId> {
    Mono<Long> countByCategory(ProductCategory category);
    Flux<ProductDocument> findByCategoryOrderByNameDesc(ProductCategory category, Pageable pageable);
}
