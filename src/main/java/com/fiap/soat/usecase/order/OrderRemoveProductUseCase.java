package com.fiap.soat.usecase.order;

import com.fiap.soat.entities.dto.order.OrderDTO;
import com.fiap.soat.entities.exception.BusinessException;
import com.fiap.soat.entities.exception.NotFoundException;
import com.fiap.soat.gateways.db.OrderDatabaseGateway;
import com.fiap.soat.usecase.UseCase;
import lombok.AllArgsConstructor;
import org.apache.commons.lang3.tuple.Pair;
import reactor.core.publisher.Mono;

import static com.fiap.soat.entities.enums.OrderStatus.CREATED;
import static com.fiap.soat.entities.enums.ServiceError.ORDER_PRODUCT_NOT_EXISTS_IN_LIST;
import static com.fiap.soat.entities.enums.ServiceError.ORDER_STATUS_MUST_BE_CREATED;

@AllArgsConstructor
public class OrderRemoveProductUseCase implements UseCase<Pair<String, String>, OrderDTO> {
  private final OrderDatabaseGateway gateway;
  private final UseCase<String, OrderDTO> orderGetByIdUseCase;

  @Override
  public Mono<OrderDTO> execute(Pair<String, String> pair) {
    return getOrder(pair.getLeft())
        .flatMap(order -> thereMustBeAProductInTheList(order, pair.getRight()))
        .map(order -> removeProduct(order, pair.getRight()))
        .flatMap(gateway::save);
  }

  private Mono<OrderDTO> getOrder(String orderId) {
    return orderGetByIdUseCase
        .execute(orderId)
        .filter(order -> CREATED.equals(order.getStatus()))
        .switchIfEmpty(Mono.error(new BusinessException(ORDER_STATUS_MUST_BE_CREATED)));
  }

  private Mono<OrderDTO> thereMustBeAProductInTheList(OrderDTO order, String productId) {
    return Mono.just(order)
        .filter(o -> o.existsProductInList(productId))
        .switchIfEmpty(Mono.error(new NotFoundException(ORDER_PRODUCT_NOT_EXISTS_IN_LIST)));
  }

  private OrderDTO removeProduct(OrderDTO order, String productId) {
    order.removeProductInList(productId);
    return order;
  }
}
