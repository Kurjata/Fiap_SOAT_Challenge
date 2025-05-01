package com.fiap.soat.service;

import com.fiap.soat.exception.NotFoundException;
import com.fiap.soat.mapper.OrderMapper;
import com.fiap.soat.model.dto.order.OrderAddProductDTO;
import com.fiap.soat.model.dto.order.OrderDTO;
import com.fiap.soat.repository.OrderRepository;
import lombok.AllArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.util.function.Predicate;

import static com.fiap.soat.model.enums.ServiceError.ORDER_NOT_EXISTS;
import static com.fiap.soat.model.enums.ServiceError.ORDER_PRODUCT_EXISTS_IN_LIST;

@Service
@AllArgsConstructor
public class OrderService {
  private OrderRepository orderRepository;
  private OrderMapper orderMapper;

  private ProductService productService;

  public Mono<OrderDTO> save(OrderDTO dto) {
    return Mono.just(dto)
        .map(this.orderMapper::createToDocument)
        .flatMap(this.orderRepository::save)
        .map(this.orderMapper::toDTO);
  }

  public Mono<OrderDTO> addProduct(OrderAddProductDTO dto) {
    return Mono.just(dto.getOrderId())
        .flatMap(this::getById)
        .flatMap(
            order ->
                productService
                    .getById(dto.getProductId())
                    .filter(Predicate.not(product -> order.existsProductInList(product.getId())))
                    .map(this.orderMapper::toOrderProductDTO)
                    .map(
                        product -> {
                          order.getProducts().add(product);
                          return order;
                        })
                    .flatMap(this::save)
                    .switchIfEmpty(
                        Mono.error(new NotFoundException(ORDER_PRODUCT_EXISTS_IN_LIST))));
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
