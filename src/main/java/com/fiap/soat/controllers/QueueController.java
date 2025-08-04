package com.fiap.soat.controllers;


import com.fiap.soat.entities.dto.queue.QueueDTO;
import com.fiap.soat.entities.dto.queue.QueueFilterDTO;
import com.fiap.soat.gateways.db.QueueDatabaseGateway;
import com.fiap.soat.usecase.queue.QueueGetByFilterUseCase;
import com.fiap.soat.usecase.queue.QueueGetByIdUseCase;
import com.fiap.soat.usecase.queue.QueueNextStatusUseCase;
import org.springframework.data.domain.PageImpl;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
public class QueueController {
  private final QueueGetByFilterUseCase queueGetByFilterUseCase;
  private final QueueNextStatusUseCase queueNextStatusUseCase;

  public QueueController(QueueDatabaseGateway queueGateway) {
    var queueGetByIdUseCase = new QueueGetByIdUseCase(queueGateway);
    this.queueGetByFilterUseCase = new QueueGetByFilterUseCase(queueGateway);
    this.queueNextStatusUseCase = new QueueNextStatusUseCase(queueGateway, queueGetByIdUseCase);
  }

  public Mono<PageImpl<QueueDTO>> getByFilter(QueueFilterDTO filter) {
    return queueGetByFilterUseCase.execute(filter);
  }

  public Mono<QueueDTO> nextStatus(String id) {
    return queueNextStatusUseCase.execute(id);
  }
}
