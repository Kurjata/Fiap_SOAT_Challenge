package com.fiap.soat.gateways.db;

import com.fiap.soat.entities.dto.product.ProductDTO;
import com.fiap.soat.entities.enums.ProductCategory;
import org.springframework.data.domain.Pageable;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface ProductDatabaseGateway extends DataBaseGateway<ProductDTO> {
  Mono<Long> countByCategory(ProductCategory category);

  Flux<ProductDTO> findByCategoryOrderByNameDesc(ProductCategory category, Pageable pageable);

  Mono<Void> delete(ProductDTO product);
}
