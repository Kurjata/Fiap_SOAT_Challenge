package com.fiap.service.impl;

import static com.fiap.enums.ServiceError.PRODUCT_NOT_EXISTS;

import com.fiap.dto.product.Product;
import com.fiap.enums.ProductCategory;
import com.fiap.exception.NotFoundException;
import com.fiap.service.ProductService;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;
import com.fiap.repository.ProductRepository;

@Service
public class ProductServiceImpl implements ProductService {

  private final ProductRepository repository;

  public ProductServiceImpl(ProductRepository repository) {
    this.repository = repository;
  }

  public Mono<Product> create(Product product) {
    return Mono.just(product).flatMap(repository::save);
  }

  public Mono<Void> delete(String id) {
    return getById(id).flatMap(repository::delete);
  }

  public Mono<Product> getById(String id) {
    return repository
        .findById(id)
        .switchIfEmpty(Mono.error(new NotFoundException(PRODUCT_NOT_EXISTS)));
  }

  public Mono<Product> update(Product product) {
    return getById(product.getId()).flatMap(p -> repository.save(product));
  }

  public Mono<PageImpl<Product>> getByCategory(ProductCategory category, Pageable pageable) {
    return repository
        .countByCategory(category)
        .flatMap(
            total ->
                repository
                    .findByCategory(category, pageable)
                    .collectList()
                    .map(list -> new PageImpl<>(list, pageable, total)));
  }
}
