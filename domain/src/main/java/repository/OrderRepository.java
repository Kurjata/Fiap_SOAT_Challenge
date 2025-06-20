package repository;

import dto.order.Order;
import dto.order.OrderFilter;
import dto.order.OrderProduct;
import dto.product.Product;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface OrderRepository {
  Mono<Order> save(Order order);

  Mono<Order> findById(String id);

  Flux<Order> findByFilter(OrderFilter filter);

  Mono<Long> getCountByFilter(OrderFilter filter);

  Mono<OrderProduct> getOrderProduct(Product product);
}
