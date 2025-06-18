package com.fiap.service;


import dto.charge.ChargeDTO;
import exception.BusinessException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.util.function.Predicate;

import static enums.OrderStatus.CREATED;
import static enums.OrderStatus.WAITING_FOR_PAYMENT;
import static enums.ServiceError.CHARGE_ORDER_PRODUCTS_EMPTY;
import static enums.ServiceError.CHARGE_ORDER_STATUS_NOT_CREATED;

@Service
@AllArgsConstructor
public class ChargeService {

  private OrderService orderService;
  private QueueService queueService;

  public Mono<ChargeDTO> create(ChargeDTO dto) {
    return Mono.just(dto.getOrderId())
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
        .thenReturn(dto);
  }

  public Mono<ChargeDTO> payment(ChargeDTO dto) {
    return Mono.just(dto.getOrderId())
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
              dto.setQueueId(queue.getId());
              return dto;
            });
  }
}
