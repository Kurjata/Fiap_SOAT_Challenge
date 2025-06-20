package com.fiap.service;

import com.fiap.dto.product.Product;
import com.fiap.enums.ProductCategory;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import reactor.core.publisher.Mono;

public interface ProductService {
  Mono<Product> create(Product product);

  Mono<Product> getById(String id);

  Mono<Product> update(Product product);

  Mono<Void> delete(String id);

  Mono<PageImpl<Product>> getByCategory(ProductCategory category, Pageable pageable);
}
