package com.fiap.soat.usecase.order;

import com.fiap.soat.entities.dto.FilterDTO;
import com.fiap.soat.entities.dto.order.OrderDTO;
import com.fiap.soat.entities.dto.order.OrderFilterDTO;
import com.fiap.soat.gateways.db.OrderDatabaseGateway;
import com.fiap.soat.usecase.FilterUseCase;
import com.fiap.soat.usecase.UseCase;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.PageImpl;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@AllArgsConstructor
public class OrderGetByFilterUseCase extends FilterUseCase<OrderDTO>
    implements UseCase<OrderFilterDTO, PageImpl<OrderDTO>> {
  private final OrderDatabaseGateway gateway;

  @Override
  public Mono<PageImpl<OrderDTO>> execute(OrderFilterDTO filter) {
    return this.findPage(filter);
  }

  @Override
  protected Mono<Long> countByFilter(FilterDTO filter) {
    return gateway.countByFilter(filter);
  }

  @Override
  protected Flux<OrderDTO> findByFilter(FilterDTO filter) {
    return gateway.findByFilter(filter);
  }
}
