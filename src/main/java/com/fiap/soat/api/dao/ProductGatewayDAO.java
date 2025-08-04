package com.fiap.soat.api.dao;

import com.fiap.soat.api.dao.repository.ProductRepository;
import com.fiap.soat.entities.dto.product.ProductDTO;
import com.fiap.soat.entities.enums.ProductCategory;
import com.fiap.soat.gateways.db.ProductDatabaseGateway;
import com.fiap.soat.gateways.mapper.ProductGatewayMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Component
@RequiredArgsConstructor
public class ProductGatewayDAO implements ProductDatabaseGateway {

  private final ProductRepository repository;
  private final ProductGatewayMapper mapper;

  @Override
  public Mono<Long> countByCategory(ProductCategory category) {
    return repository.countByCategory(category);
  }

  @Override
  public Flux<ProductDTO> findByCategoryOrderByNameDesc(
      ProductCategory category, Pageable pageable) {
    return repository.findByCategoryOrderByNameDesc(category, pageable).map(mapper::toDTO);
  }

  @Override
  public Mono<Void> delete(ProductDTO product) {
    return Mono.just(product).map(mapper::toDocument).flatMap(repository::delete);
  }

  @Override
  public Mono<ProductDTO> findById(String id) {
    return this.stringToId(id).flatMap(repository::findById).map(mapper::toDTO);
  }

  @Override
  public Mono<ProductDTO> save(ProductDTO product) {
    return Mono.just(product).map(mapper::toDocument).flatMap(repository::save).map(mapper::toDTO);
  }
}
