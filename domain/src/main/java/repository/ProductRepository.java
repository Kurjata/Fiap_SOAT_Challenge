package repository;

import dto.product.Product;
import enums.ProductCategory;
import java.util.List;
import reactor.core.publisher.Mono;

public interface ProductRepository {
  Mono<Product> save(Product product);

  Mono<Product> findById(String id);

  Mono<List<Product>> findByCategory(ProductCategory category);

  Mono<Long> countByCategory(ProductCategory category);

  Mono<Void> delete(Product product);
}
