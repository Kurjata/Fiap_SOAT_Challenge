package repository;

import dto.product.Product;
import enums.ProductCategory;
import java.util.List;

import org.springframework.data.domain.Pageable;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface ProductRepository {
  Mono<Product> save(Product product);

  Mono<Product> findById(String id);

  Flux<Product> findByCategory(ProductCategory category, Pageable pageable);

  Mono<Long> countByCategory(ProductCategory category);

  Mono<Void> delete(Product product);
}
