package com.fiap.soat.usecase.order;

import com.fiap.soat.usecase.UseCase;

import com.fiap.soat.entities.dto.order.OrderAddProductDTO;
import com.fiap.soat.entities.dto.order.OrderDTO;
import com.fiap.soat.entities.dto.order.OrderProductDTO;
import com.fiap.soat.entities.dto.product.ProductDTO;
import com.fiap.soat.entities.exception.BusinessException;
import com.fiap.soat.entities.exception.NotFoundException;
import com.fiap.soat.gateways.db.OrderDatabaseGateway;
import com.fiap.soat.gateways.mapper.OrderGatewayMapper;
import lombok.AllArgsConstructor;
import reactor.core.publisher.Mono;

import java.util.function.Predicate;

import static com.fiap.soat.entities.enums.OrderStatus.CREATED;
import static com.fiap.soat.entities.enums.ServiceError.ORDER_PRODUCT_EXISTS_IN_LIST;
import static com.fiap.soat.entities.enums.ServiceError.ORDER_STATUS_MUST_BE_CREATED;


@AllArgsConstructor
public class OrderAddProductUseCase implements UseCase<OrderAddProductDTO, OrderDTO> {
  private final OrderDatabaseGateway gateway;
  private OrderGatewayMapper mapper;
  private final UseCase<String, OrderDTO> orderGetByIdUseCase;
  private final UseCase<String, ProductDTO> productGetByIdUseCase;

  @Override
  public Mono<OrderDTO> execute(OrderAddProductDTO dto) {
    return orderGetByIdUseCase
        .execute(dto.getOrderId())
        .filter(order -> CREATED.equals(order.getStatus()))
        .switchIfEmpty(Mono.error(new BusinessException(ORDER_STATUS_MUST_BE_CREATED)))
        .flatMap(order -> create(order, dto));
  }

  private Mono<OrderDTO> create(OrderDTO order, OrderAddProductDTO dto) {
    return productGetByIdUseCase
        .execute(dto.getProductId())
        .map(this.mapper::toOrderProductDTO)
        .filter(Predicate.not(product -> order.existsProductInList(product.getId())))
        .switchIfEmpty(Mono.error(new NotFoundException(ORDER_PRODUCT_EXISTS_IN_LIST)))
        .map(product -> this.addProductInList(product, order, dto.getQuantity()))
        .flatMap(gateway::save);
  }

  private OrderDTO addProductInList(OrderProductDTO product, OrderDTO order, Integer quantity) {
    product.setQuantity(quantity);
    order.getProducts().add(product);
    return order;
  }
}
