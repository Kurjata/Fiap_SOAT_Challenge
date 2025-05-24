package com.fiap.soat.service;

import com.fiap.soat.exception.BusinessException;
import com.fiap.soat.exception.NotFoundException;
import com.fiap.soat.mapper.QueueMapper;
import com.fiap.soat.model.document.queue.QueueDocument;
import com.fiap.soat.model.document.queue.QueueHistoryDocument;
import com.fiap.soat.model.dto.order.OrderDTO;
import com.fiap.soat.model.dto.queue.QueueDTO;
import com.fiap.soat.model.dto.queue.QueueFilterDTO;
import com.fiap.soat.model.enums.QueueTrackingStatus;
import com.fiap.soat.model.enums.ServiceError;
import com.fiap.soat.repository.QueueRepository;
import lombok.AllArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.data.domain.PageImpl;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.time.LocalDateTime;

import static com.fiap.soat.model.enums.QueueTrackingStatus.FINISHED;
import static com.fiap.soat.model.enums.QueueTrackingStatus.IN_PREPARATION;
import static com.fiap.soat.model.enums.QueueTrackingStatus.READY;

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

  public Mono<QueueDTO> nextStatus(String id) {
    return Mono.just(id)
        .filter(ObjectId::isValid)
        .map(ObjectId::new)
        .flatMap(this.queueRepository::findById)
        .flatMap(this::envolveStatus)
        .map(this::addHistory)
        .flatMap(this.queueRepository::save)
        .map(this.queueMapper::toDTO)
        .switchIfEmpty(Mono.error(new NotFoundException(ServiceError.QUEUE_NOT_FOUND)));
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

  private Mono<QueueDocument> envolveStatus(QueueDocument document) {
    return switch (document.getStatus()) {
      case RECEIVED -> Mono.just(document).map(d -> setStatus(d, IN_PREPARATION));
      case IN_PREPARATION ->
          Mono.just(document).map(d -> setStatus(d, READY));
      case READY -> Mono.just(document).map(d -> setStatus(d, FINISHED));
      case FINISHED ->
          Mono.error(new BusinessException(ServiceError.QUEUE_ENVOLVE_STATUS_FINISHED));
    };
  }

  private QueueDocument setStatus(QueueDocument document, QueueTrackingStatus status) {
    document.setStatus(status);
    document.setTimestampCurrentStatus(LocalDateTime.now());
    return document;
  }
}
