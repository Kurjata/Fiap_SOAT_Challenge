package com.fiap.service;

import dto.product.Product;
import enums.ProductCategory;
import exception.NotFoundException;
import org.bson.types.ObjectId;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;
import repository.ProductRepository;

import static enums.ServiceError.PRODUCT_NOT_EXISTS;

@Service
public class ProductServiceImpl {

  private ProductRepository repository;

  public ProductServiceImpl(ProductRepository repository) {
    this.repository = repository;
  }

  public Mono<Product> create(Product dto) {
    return Mono.just(dto).flatMap(repository::save);
  }

  public Mono<Product> update(Product dto) {
    return Mono.just(dto.getId()).flatMap(this::getById);
  }

  public Mono<Void> delete(String id) {
    return getById(id).map(productMapper::toDocument).flatMap(productRepository::delete);
  }

  public Mono<Product> getById(String id) {
    return Mono.just(id)
        .filter(ObjectId::isValid)
        .map(ObjectId::new)
        .flatMap(productRepository::findById)
        .map(productMapper::toDTO)
        .switchIfEmpty(Mono.error(new NotFoundException(PRODUCT_NOT_EXISTS)));
  }

  public Mono<PageImpl<Product>> getByCategory(ProductCategory category, Pageable pageable) {
    return this.productRepository
        .countByCategory(category)
        .flatMap(
            total ->
                this.productRepository
                    .findByCategoryOrderByNameDesc(category, pageable)
                    .map(this.productMapper::toDTO)
                    .collectList()
                    .map(list -> new PageImpl<>(list, pageable, total)));
  }
}
