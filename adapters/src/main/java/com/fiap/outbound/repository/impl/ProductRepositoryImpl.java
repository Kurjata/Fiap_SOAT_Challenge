package com.fiap.outbound.repository.impl;

import com.fiap.inbound.mapper.ProductMapper;
import com.fiap.outbound.repository.MongoProductRepository;
import dto.product.Product;
import enums.ProductCategory;

import lombok.RequiredArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import repository.ProductRepository;

@Component
@RequiredArgsConstructor
public class ProductRepositoryImpl implements ProductRepository {
  private final ProductMapper mapper;
  private final MongoProductRepository repository;

  @Override
  public Mono<Product> save(Product product) {
    return Mono.just(product)
        .map(mapper::toDocument)
        .flatMap(repository::save)
        .map(mapper::toProduct);
  }

  @Override
  public Mono<Product> findById(String id) {
    return Mono.just(id)
        .filter(ObjectId::isValid)
        .map(ObjectId::new)
        .flatMap(repository::findById)
        .map(mapper::toProduct);
  }

  @Override
  public Flux<Product> findByCategory(ProductCategory category, Pageable pageable) {
    return repository.findByCategoryOrderByNameDesc(category, pageable).map(mapper::toProduct);
  }

  @Override
  public Mono<Long> countByCategory(ProductCategory category) {
    return repository.countByCategory(category);
  }

  @Override
  public Mono<Void> delete(Product product) {
    return Mono.just(product).map(mapper::toDocument).flatMap(repository::delete);
  }
}
