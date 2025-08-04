package com.fiap.soat.usecase.queue;


import com.fiap.soat.usecase.UseCase;
import com.fiap.soat.entities.dto.FilterDTO;
import com.fiap.soat.entities.dto.queue.QueueDTO;
import com.fiap.soat.entities.dto.queue.QueueFilterDTO;
import com.fiap.soat.gateways.db.QueueDatabaseGateway;
import com.fiap.soat.usecase.FilterUseCase;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.PageImpl;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@AllArgsConstructor
public class QueueGetByFilterUseCase extends FilterUseCase<QueueDTO>
    implements UseCase<QueueFilterDTO, PageImpl<QueueDTO>> {
  private final QueueDatabaseGateway gateway;

  @Override
  public Mono<PageImpl<QueueDTO>> execute(QueueFilterDTO filter) {
    return this.findPage(filter);
  }

  @Override
  protected Mono<Long> countByFilter(FilterDTO filter) {
    return gateway.countByFilter(filter);
  }

  @Override
  protected Flux<QueueDTO> findByFilter(FilterDTO filter) {
    return gateway.findByFilter(filter);
  }
}
