package com.fiap.soat.usecase.product;

import com.fiap.soat.usecase.UseCase;
import com.fiap.soat.entities.dto.product.ProductDTO;
import com.fiap.soat.gateways.db.ProductDatabaseGateway;
import lombok.AllArgsConstructor;
import reactor.core.publisher.Mono;

@AllArgsConstructor
public class ProductDeleteUseCase implements UseCase<String, Void> {
  private final ProductDatabaseGateway gateway;
  private final UseCase<String, ProductDTO> getByIdUseCase;

  @Override
  public Mono<Void> execute(String id) {
    return getByIdUseCase.execute(id).flatMap(gateway::delete);
  }
}
