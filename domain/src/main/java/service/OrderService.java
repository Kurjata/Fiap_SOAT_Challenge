package service;

import dto.order.Order;
import dto.order.OrderAddProduct;
import dto.order.OrderFilter;
import org.springframework.data.domain.PageImpl;
import reactor.core.publisher.Mono;

public interface OrderService {
  Mono<Order> save(Order order);

  Mono<Order> addProduct(OrderAddProduct orderAddProduct);

  Mono<Order> getById(String id);

  Mono<Order> removeProduct(String orderId, String productId);

  Mono<Order> cancel(String orderId);

  Mono<PageImpl<Order>> getByFilter(OrderFilter filter);
}
