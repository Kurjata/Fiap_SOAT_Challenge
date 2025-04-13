package com.fiap.soat.service;

import com.fiap.soat.mapper.ProductMapper;
import com.fiap.soat.model.dto.product.ProductDTO;
import com.fiap.soat.repository.ProductRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
@AllArgsConstructor
public class ProductService {
  private ProductMapper productMapper;
  private ProductRepository productRepository;

  public Mono<ProductDTO> create(ProductDTO dto) {
    return Mono.empty();
  }
}
