package com.fiap.soat.service;

import com.fiap.soat.exception.BusinessException;
import com.fiap.soat.exception.NotFoundException;
import com.fiap.soat.mapper.OrderMapper;
import com.fiap.soat.model.dto.order.OrderAddProductDTO;
import com.fiap.soat.model.dto.order.OrderDTO;
import com.fiap.soat.model.dto.order.OrderProductDTO;
import com.fiap.soat.repository.OrderRepository;
import lombok.AllArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.util.function.Predicate;

import static com.fiap.soat.model.enums.OrderStatus.CREATED;
import static com.fiap.soat.model.enums.ServiceError.ORDER_NOT_EXISTS;
import static com.fiap.soat.model.enums.ServiceError.ORDER_PRODUCT_EXISTS_IN_LIST;
import static com.fiap.soat.model.enums.ServiceError.ORDER_STATUS_MUST_BE_CREATED;
import static com.fiap.soat.model.enums.ServiceError.PRODUCT_NOT_EXISTS;

@Service
@AllArgsConstructor
public class OrderService {
  private OrderRepository orderRepository;
  private OrderMapper orderMapper;

  private ProductService productService;

  public Mono<OrderDTO> save(OrderDTO dto) {
    return Mono.just(dto)
        .map(this.orderMapper::toDocument)
        .flatMap(this.orderRepository::save)
        .map(this.orderMapper::toDTO);
  }

  public Mono<OrderDTO> addProduct(OrderAddProductDTO dto) {
    return Mono.just(dto.getOrderId())
        .flatMap(this::getById)
        .filter(order -> CREATED.equals(order.getStatus()))
        .switchIfEmpty(Mono.error(new BusinessException(ORDER_STATUS_MUST_BE_CREATED)))
        .flatMap(
            order ->
                getProductById(dto.getProductId())
                    .filter(Predicate.not(product -> order.existsProductInList(product.getId())))
                    .switchIfEmpty(Mono.error(new NotFoundException(ORDER_PRODUCT_EXISTS_IN_LIST)))
                    .map(
                        product -> {
                          product.setQuantity(dto.getQuantity());
                          order.getProducts().add(product);
                          return order;
                        })
                    .flatMap(this::save));
  }

  private Mono<OrderProductDTO> getProductById(String id) {
    return productService
        .getById(id)
        .map(this.orderMapper::toOrderProductDTO)
        .switchIfEmpty(Mono.error(new NotFoundException(PRODUCT_NOT_EXISTS)));
  }

  private Mono<OrderDTO> getById(String id) {
    return Mono.just(id)
        .filter(ObjectId::isValid)
        .map(ObjectId::new)
        .flatMap(orderRepository::findById)
        .map(this.orderMapper::toDTO)
        .switchIfEmpty(Mono.error(new NotFoundException(ORDER_NOT_EXISTS)));
  }
}
