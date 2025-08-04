package com.fiap.soat.controllers;


import com.fiap.soat.entities.dto.product.ProductDTO;
import com.fiap.soat.entities.enums.ProductCategory;
import com.fiap.soat.gateways.db.ProductDatabaseGateway;
import com.fiap.soat.usecase.product.ProductCreateUseCase;
import com.fiap.soat.usecase.product.ProductDeleteUseCase;
import com.fiap.soat.usecase.product.ProductGetByIdUseCase;
import com.fiap.soat.usecase.product.ProductGetPageByCategoryUseCase;
import com.fiap.soat.usecase.product.ProductUpdateUseCase;
import org.apache.commons.lang3.tuple.Pair;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
public class ProductController {
  private final ProductCreateUseCase productCreateUseCase;
  private final ProductDeleteUseCase productDeleteUseCase;
  private final ProductUpdateUseCase productUpdateUseCase;
  private final ProductGetPageByCategoryUseCase productGetPageByCategoryUseCase;

  public ProductController(ProductDatabaseGateway gateway) {
    var productGetByIdUseCase = new ProductGetByIdUseCase(gateway);
    this.productCreateUseCase = new ProductCreateUseCase(gateway);
    this.productDeleteUseCase = new ProductDeleteUseCase(gateway, productGetByIdUseCase);
    this.productUpdateUseCase = new ProductUpdateUseCase(gateway, productGetByIdUseCase);
    this.productGetPageByCategoryUseCase = new ProductGetPageByCategoryUseCase(gateway);
  }

  public Mono<ProductDTO> create(ProductDTO product) {
    return productCreateUseCase.execute(product);
  }

  public Mono<ProductDTO> update(ProductDTO product) {
    return productUpdateUseCase.execute(product);
  }

  public Mono<Void> delete(String id) {
    return productDeleteUseCase.execute(id);
  }

  public Mono<PageImpl<ProductDTO>> getByCategory(ProductCategory category, Pageable pageable) {
    return productGetPageByCategoryUseCase.execute(Pair.of(category, pageable));
  }
}
