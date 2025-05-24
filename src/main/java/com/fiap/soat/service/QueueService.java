package com.fiap.soat.service;

import com.fiap.soat.mapper.QueueMapper;
import com.fiap.soat.model.document.queue.QueueDocument;
import com.fiap.soat.model.document.queue.QueueHistoryDocument;
import com.fiap.soat.model.dto.order.OrderDTO;
import com.fiap.soat.model.dto.queue.QueueDTO;
import com.fiap.soat.model.dto.queue.QueueFilterDTO;
import com.fiap.soat.repository.QueueRepository;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.PageImpl;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
@AllArgsConstructor
public class QueueService {
  private QueueRepository queueRepository;
  private QueueMapper queueMapper;

  public Mono<QueueDTO> create(OrderDTO order) {
    return Mono.just(order)
        .map(this.queueMapper::create)
        .map(this::addHistory)
        .flatMap(this.queueRepository::save)
        .map(this.queueMapper::toDTO);
  }

  public Mono<PageImpl<QueueDTO>> getByFilter(QueueFilterDTO filter) {
    return this.queueRepository
        .getCountByFilter(filter)
        .flatMap(
            total ->
                this.queueRepository
                    .getByFilter(filter)
                    .map(this.queueMapper::toDTO)
                    .collectList()
                    .map(list -> new PageImpl<>(list, filter.getPageable(), total)));
  }

  private QueueDocument addHistory(QueueDocument document) {
    document
        .getHistory()
        .add(
            QueueHistoryDocument.builder()
                .status(document.getStatus())
                .timestamp(document.getTimestampCurrentStatus())
                .build());
    return document;
  }
}
