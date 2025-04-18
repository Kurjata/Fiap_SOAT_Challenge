package com.fiap.soat.service;

import com.fiap.soat.exception.NotFoundException;
import com.fiap.soat.mapper.ProductMapper;
import com.fiap.soat.model.document.product.ProductDocument;
import com.fiap.soat.model.dto.product.ProductDTO;
import com.fiap.soat.repository.ProductRepository;
import lombok.AllArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import static com.fiap.soat.model.enums.ServiceError.PRODUCT_NOT_EXISTS;

@Service
@AllArgsConstructor
public class ProductService {
  private ProductMapper productMapper;
  private ProductRepository productRepository;

  public Mono<ProductDTO> create(ProductDTO dto) {
    return Mono.just(dto)
        .map(productMapper::toDocument)
        .flatMap(productRepository::save)
        .map(productMapper::toDTO);
  }

  public Mono<ProductDTO> update(ProductDTO dto) {
    return Mono.just(dto.getId()).flatMap(this::getById).map(productMapper::toDTO);
  }

  public Mono<Void> delete(String id) {
    return getById(id).flatMap(productRepository::delete);
  }

  private Mono<ProductDocument> getById(String id) {
    return Mono.just(id)
        .filter(ObjectId::isValid)
        .map(ObjectId::new)
        .flatMap(productRepository::findById)
        .switchIfEmpty(Mono.error(new NotFoundException(PRODUCT_NOT_EXISTS)));
  }
}
