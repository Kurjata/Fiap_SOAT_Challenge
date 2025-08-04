package com.fiap.soat.usecase.product;


import com.fiap.soat.entities.dto.product.ProductDTO;
import com.fiap.soat.gateways.db.ProductDatabaseGateway;
import com.fiap.soat.usecase.UseCase;
import lombok.AllArgsConstructor;
import reactor.core.publisher.Mono;

@AllArgsConstructor
public class ProductCreateUseCase implements UseCase<ProductDTO, ProductDTO> {
  private final ProductDatabaseGateway gateway;

  @Override
  public Mono<ProductDTO> execute(ProductDTO product) {
    return gateway.save(product);
  }
}
