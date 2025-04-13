package com.fiap.soat.service;

import com.fiap.soat.mapper.ProductMapper;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class ProductService {
    private ProductMapper productMapper;
}
