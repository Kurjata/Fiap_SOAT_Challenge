package com.fiap.service;

import com.fiap.dto.order.Order;
import com.fiap.dto.queue.Queue;
import com.fiap.dto.queue.QueueFilter;
import org.springframework.data.domain.PageImpl;
import reactor.core.publisher.Mono;

public interface QueueService {
  Mono<Queue> create(Order order);

  Mono<PageImpl<Queue>> getByFilter(QueueFilter filter);

  Mono<Queue> nextStatus(String id);
}
