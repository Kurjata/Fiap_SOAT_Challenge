package com.fiap.soat.usecase.product;

import com.fiap.soat.usecase.UseCase;
import com.fiap.soat.entities.dto.product.ProductDTO;
import com.fiap.soat.gateways.db.ProductDatabaseGateway;
import lombok.AllArgsConstructor;
import reactor.core.publisher.Mono;

@AllArgsConstructor
public class ProductUpdateUseCase implements UseCase<ProductDTO, ProductDTO> {
  private final ProductDatabaseGateway gateway;
  private final UseCase<String, ProductDTO> getByIdUseCase;

  @Override
  public Mono<ProductDTO> execute(ProductDTO product) {
    return getByIdUseCase.execute(product.getId()).thenReturn(product).flatMap(gateway::save);
  }
}
