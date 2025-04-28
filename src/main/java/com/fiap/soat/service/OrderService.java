package com.fiap.soat.service;

import com.fiap.soat.mapper.OrderMapper;
import com.fiap.soat.repository.OrderRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class OrderService {
    private OrderRepository orderRepository;
    private OrderMapper orderMapper;
}
