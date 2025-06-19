package com.fiap.outbound.repository;

import com.fiap.inbound.mapper.ProductMapper;
import dto.product.Product;
import enums.ProductCategory;
import java.util.List;
import reactor.core.publisher.Mono;
import repository.ProductRepository;

public class ProductRepositoryImpl implements ProductRepository {
  private ProductMapper mapper;
  private MongoProductRepository repository;

  @Override
  public Mono<Product> save(Product product) {
    return Mono.just(product)
        .map(mapper::toDocument)
        .flatMap(repository::save)
        .map(mapper::toProduct);
  }

  @Override
  public Mono<Product> findById(String id) {
    return null;
  }

  @Override
  public Mono<List<Product>> findByCategory(ProductCategory category) {
    return Mono.empty();
  }

  @Override
  public Mono<Long> countByCategory(ProductCategory category) {
    return null;
  }

  @Override
  public Mono<Void> delete(Product product) {
    return null;
  }
}
