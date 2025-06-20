package com.fiap.service.impl;

import static com.fiap.enums.OrderStatus.CANCELED;
import static com.fiap.enums.OrderStatus.CREATED;
import static com.fiap.enums.ServiceError.ORDER_NOT_EXISTS;
import static com.fiap.enums.ServiceError.ORDER_PRODUCT_EXISTS_IN_LIST;
import static com.fiap.enums.ServiceError.ORDER_PRODUCT_NOT_EXISTS_IN_LIST;
import static com.fiap.enums.ServiceError.ORDER_STATUS_IS_ALREADY_CANCELLED;
import static com.fiap.enums.ServiceError.ORDER_STATUS_MUST_BE_CREATED;
import static com.fiap.enums.ServiceError.ORDER_STATUS_PAID_NOT_CANCEL;
import static com.fiap.enums.ServiceError.PRODUCT_NOT_EXISTS;

import com.fiap.dto.order.Order;
import com.fiap.dto.order.OrderAddProduct;
import com.fiap.dto.order.OrderFilter;
import com.fiap.dto.order.OrderProduct;
import com.fiap.exception.BusinessException;
import com.fiap.exception.NotFoundException;
import java.util.function.Predicate;

import com.fiap.service.OrderService;
import com.fiap.service.ProductService;
import org.springframework.data.domain.PageImpl;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;
import com.fiap.repository.OrderRepository;

@Service
public class OrderServiceImpl implements OrderService {
  private final OrderRepository orderRepository;
  private final ProductService productService;

  public OrderServiceImpl(OrderRepository orderRepository, ProductService productService) {
    this.orderRepository = orderRepository;
    this.productService = productService;
  }

  public Mono<Order> save(Order order) {
    return Mono.just(order).flatMap(this.orderRepository::save);
  }

  public Mono<Order> addProduct(OrderAddProduct orderAddProduct) {
    return Mono.just(orderAddProduct.getOrderId())
        .flatMap(this::getById)
        .filter(order -> CREATED.equals(order.getStatus()))
        .switchIfEmpty(Mono.error(new BusinessException(ORDER_STATUS_MUST_BE_CREATED)))
        .flatMap(
            order ->
                getProductById(orderAddProduct.getProductId())
                    .filter(Predicate.not(product -> order.existsProductInList(product.getId())))
                    .switchIfEmpty(Mono.error(new NotFoundException(ORDER_PRODUCT_EXISTS_IN_LIST)))
                    .map(
                        product -> {
                          product.setQuantity(orderAddProduct.getQuantity());
                          order.getProducts().add(product);
                          return order;
                        })
                    .flatMap(this::save));
  }

  private Mono<OrderProduct> getProductById(String id) {
    return productService
        .getById(id)
        .flatMap(orderRepository::getOrderProduct)
        .switchIfEmpty(Mono.error(new NotFoundException(PRODUCT_NOT_EXISTS)));
  }

  public Mono<Order> getById(String id) {
    return orderRepository
        .findById(id)
        .switchIfEmpty(Mono.error(new NotFoundException(ORDER_NOT_EXISTS)));
  }

  public Mono<Order> removeProduct(String orderId, String productId) {
    return getById(orderId)
        .filter(order -> CREATED.equals(order.getStatus()))
        .switchIfEmpty(Mono.error(new BusinessException(ORDER_STATUS_MUST_BE_CREATED)))
        .filter(order -> order.existsProductInList(productId))
        .switchIfEmpty(Mono.error(new NotFoundException(ORDER_PRODUCT_NOT_EXISTS_IN_LIST)))
        .map(
            order -> {
              order.removeProductInList(productId);
              return order;
            })
        .flatMap(this::save);
  }

  public Mono<Order> cancel(String orderId) {
    return getById(orderId)
        .flatMap(this::routeCancel)
        .map(
            order -> {
              order.setStatus(CANCELED);
              return order;
            })
        .flatMap(this::save);
  }

  private Mono<Order> routeCancel(Order dto) {
    return switch (dto.getStatus()) {
      case CREATED, WAITING_FOR_PAYMENT -> Mono.just(dto);
      // TODO: Ver lógica de waiting for payment quando integrar mercado pago;
      case PAID -> Mono.error(new BusinessException(ORDER_STATUS_PAID_NOT_CANCEL));
      case CANCELED -> Mono.error(new BusinessException(ORDER_STATUS_IS_ALREADY_CANCELLED));
    };
  }

  public Mono<PageImpl<Order>> getByFilter(OrderFilter filter) {
    return this.orderRepository
        .getCountByFilter(filter)
        .flatMap(
            total ->
                this.orderRepository
                    .findByFilter(filter)
                    .collectList()
                    .map(list -> new PageImpl<>(list, filter.getPageable(), total)));
  }
}
