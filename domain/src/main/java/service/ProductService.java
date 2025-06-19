package service;

import dto.product.Product;
import enums.ProductCategory;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import reactor.core.publisher.Mono;

public interface ProductService {
  Mono<Product> create(Product product);

  Mono<Product> getById(String id);

  Mono<Void> delete(String id);

  Mono<PageImpl<Product>> getByCategory(ProductCategory category, Pageable pageable);
}
