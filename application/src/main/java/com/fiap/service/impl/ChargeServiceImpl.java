package com.fiap.service.impl;

import static com.fiap.enums.OrderStatus.CREATED;
import static com.fiap.enums.OrderStatus.PAID;
import static com.fiap.enums.OrderStatus.WAITING_FOR_PAYMENT;
import static com.fiap.enums.ServiceError.CHARGE_ORDER_PRODUCTS_EMPTY;
import static com.fiap.enums.ServiceError.CHARGE_ORDER_STATUS_NOT_CREATED;

import com.fiap.dto.charge.Charge;
import com.fiap.exception.BusinessException;
import java.util.function.Predicate;

import com.fiap.service.ChargeService;
import com.fiap.service.OrderService;
import com.fiap.service.QueueService;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
public class ChargeServiceImpl implements ChargeService {

  private final OrderService orderService;
  private final QueueService queueService;

  public ChargeServiceImpl(OrderService orderService, QueueService queueService) {
    this.orderService = orderService;
    this.queueService = queueService;
  }

  public Mono<Charge> create(Charge charge) {
    return Mono.just(charge.getOrderId())
        .flatMap(this.orderService::getById)
        .filter(order -> CREATED.equals(order.getStatus()))
        .switchIfEmpty(Mono.error(new BusinessException(CHARGE_ORDER_STATUS_NOT_CREATED)))
        .filter(Predicate.not(order -> order.getProducts().isEmpty()))
        .switchIfEmpty(Mono.error(new BusinessException(CHARGE_ORDER_PRODUCTS_EMPTY)))
        .map(
            order -> {
              order.setStatus(WAITING_FOR_PAYMENT);
              return order;
            })
        .flatMap(this.orderService::save)
        .thenReturn(charge);
  }

  public Mono<Charge> payment(Charge charge) {
    return Mono.just(charge.getOrderId())
        .flatMap(this.orderService::getById)
        .filter(order -> WAITING_FOR_PAYMENT.equals(order.getStatus()))
        .map(
            order -> {
              order.setStatus(PAID);
              return order;
            })
        .flatMap(this.orderService::save)
        .flatMap(this.queueService::create)
        .map(
            queue -> {
              charge.setQueueId(queue.getId());
              return charge;
            });
  }
}
