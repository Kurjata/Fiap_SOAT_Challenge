package com.fiap.soat.usecase.product;

import com.fiap.soat.usecase.UseCase;
import com.fiap.soat.entities.dto.product.ProductDTO;
import com.fiap.soat.entities.exception.NotFoundException;
import com.fiap.soat.gateways.db.ProductDatabaseGateway;
import lombok.AllArgsConstructor;
import reactor.core.publisher.Mono;

import static com.fiap.soat.entities.enums.ServiceError.PRODUCT_NOT_EXISTS;


@AllArgsConstructor
public class ProductGetByIdUseCase implements UseCase<String, ProductDTO> {
  private final ProductDatabaseGateway gateway;

  @Override
  public Mono<ProductDTO> execute(String id) {
    return gateway
        .findById(id)
        .switchIfEmpty(Mono.error(new NotFoundException(PRODUCT_NOT_EXISTS)));
  }
}
