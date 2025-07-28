package com.fiap.soat.controllers;

import com.fiap.soat.entities.dto.order.OrderAddProductDTO;
import com.fiap.soat.entities.dto.order.OrderDTO;
import com.fiap.soat.entities.dto.order.OrderFilterDTO;
import com.fiap.soat.gateways.db.OrderDatabaseGateway;
import com.fiap.soat.gateways.db.ProductDatabaseGateway;
import com.fiap.soat.gateways.mapper.OrderGatewayMapper;
import com.fiap.soat.usecase.order.OrderAddProductUseCase;
import com.fiap.soat.usecase.order.OrderCancelUseCase;
import com.fiap.soat.usecase.order.OrderCreateUseCase;
import com.fiap.soat.usecase.order.OrderGetByFilterUseCase;
import com.fiap.soat.usecase.order.OrderGetByIdUseCase;
import com.fiap.soat.usecase.order.OrderRemoveProductUseCase;
import com.fiap.soat.usecase.product.ProductGetByIdUseCase;
import org.apache.commons.lang3.tuple.Pair;
import org.springframework.data.domain.PageImpl;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
public class OrderController {
  private final OrderCreateUseCase orderCreateUseCase;
  private final OrderGetByFilterUseCase orderGetByFilterUseCase;
  private final OrderAddProductUseCase orderAddProductUseCase;
  private final OrderRemoveProductUseCase orderRemoveProductUseCase;
  private final OrderCancelUseCase orderCancelUseCase;

  public OrderController(
      ProductDatabaseGateway productGateway,
      OrderDatabaseGateway orderGateway,
      OrderGatewayMapper orderGatewayMapper) {
    var orderGetByIdUseCase = new OrderGetByIdUseCase(orderGateway);
    var productGetByIdUseCase = new ProductGetByIdUseCase(productGateway);
    this.orderCreateUseCase = new OrderCreateUseCase(orderGateway);
    this.orderGetByFilterUseCase = new OrderGetByFilterUseCase(orderGateway);
    this.orderAddProductUseCase =
        new OrderAddProductUseCase(
            orderGateway, orderGatewayMapper, orderGetByIdUseCase, productGetByIdUseCase);
    this.orderRemoveProductUseCase =
        new OrderRemoveProductUseCase(orderGateway, orderGetByIdUseCase);
    this.orderCancelUseCase = new OrderCancelUseCase(orderGateway, orderGetByIdUseCase);
  }

  public Mono<OrderDTO> save(OrderDTO order) {
    return orderCreateUseCase.execute(order);
  }

  public Mono<PageImpl<OrderDTO>> getByFilter(OrderFilterDTO filter) {
    return orderGetByFilterUseCase.execute(filter);
  }

  public Mono<OrderDTO> cancel(String id) {
    return orderCancelUseCase.execute(id);
  }

  public Mono<OrderDTO> addProduct(OrderAddProductDTO dto) {
    return orderAddProductUseCase.execute(dto);
  }

  public Mono<OrderDTO> removeProduct(String orderId, String productId) {
    return orderRemoveProductUseCase.execute(Pair.of(orderId, productId));
  }
}
