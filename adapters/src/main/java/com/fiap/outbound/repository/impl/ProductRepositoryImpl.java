package com.fiap.outbound.repository.impl;

import com.fiap.inbound.mapper.ProductMapper;
import com.fiap.outbound.repository.MongoProductRepository;
import com.fiap.dto.product.Product;
import com.fiap.enums.ProductCategory;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import com.fiap.repository.ProductRepository;

@Component
@RequiredArgsConstructor
public class ProductRepositoryImpl extends BaseRepositoryImpl implements ProductRepository {
  private final ProductMapper mapper;
  private final MongoProductRepository repository;

  public Mono<Product> save(Product product) {
    return Mono.just(product)
        .map(mapper::toDocument)
        .flatMap(repository::save)
        .map(mapper::toProduct);
  }

  public Mono<Product> findById(String id) {
    return validateAndCreateMongoId(id).flatMap(repository::findById).map(mapper::toProduct);
  }

  public Flux<Product> findByCategory(ProductCategory category, Pageable pageable) {
    return repository.findByCategoryOrderByNameDesc(category, pageable).map(mapper::toProduct);
  }

  public Mono<Long> countByCategory(ProductCategory category) {
    return repository.countByCategory(category);
  }

  public Mono<Void> delete(Product product) {
    return Mono.just(product).map(mapper::toDocument).flatMap(repository::delete);
  }
}
