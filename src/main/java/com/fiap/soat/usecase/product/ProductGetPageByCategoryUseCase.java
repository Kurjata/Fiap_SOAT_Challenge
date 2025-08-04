package com.fiap.soat.usecase.product;

import com.fiap.soat.usecase.UseCase;
import com.fiap.soat.entities.dto.product.ProductDTO;
import com.fiap.soat.entities.enums.ProductCategory;
import com.fiap.soat.gateways.db.ProductDatabaseGateway;
import lombok.AllArgsConstructor;
import org.apache.commons.lang3.tuple.Pair;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import reactor.core.publisher.Mono;

@AllArgsConstructor
public class ProductGetPageByCategoryUseCase
    implements UseCase<Pair<ProductCategory, Pageable>, PageImpl<ProductDTO>> {

  private final ProductDatabaseGateway gateway;

  @Override
  public Mono<PageImpl<ProductDTO>> execute(Pair<ProductCategory, Pageable> pair) {
    return gateway
        .countByCategory(pair.getLeft())
        .flatMap(
            total ->
                gateway
                    .findByCategoryOrderByNameDesc(pair.getLeft(), pair.getRight())
                    .collectList()
                    .map(list -> new PageImpl<>(list, pair.getRight(), total)));
  }
}
