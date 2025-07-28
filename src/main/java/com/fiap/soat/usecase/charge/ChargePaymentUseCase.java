package com.fiap.soat.usecase.charge;

import com.fiap.soat.entities.dto.charge.ChargeDTO;
import com.fiap.soat.entities.dto.order.OrderDTO;
import com.fiap.soat.entities.dto.queue.QueueDTO;
import com.fiap.soat.entities.enums.OrderStatus;
import com.fiap.soat.usecase.UseCase;
import lombok.AllArgsConstructor;
import org.apache.commons.lang3.tuple.Pair;
import reactor.core.publisher.Mono;

import static com.fiap.soat.entities.enums.OrderStatus.PAID;
import static com.fiap.soat.entities.enums.OrderStatus.WAITING_FOR_PAYMENT;

@AllArgsConstructor
public class ChargePaymentUseCase implements UseCase<ChargeDTO, ChargeDTO> {
  private final UseCase<String, OrderDTO> orderGetByIdUseCase;
  private final UseCase<Pair<OrderStatus, OrderDTO>, OrderDTO> orderSetStatusUseCase;
  private final UseCase<OrderDTO, QueueDTO> queueCreateUseCase;

  @Override
  public Mono<ChargeDTO> execute(ChargeDTO charge) {
    return Mono.just(charge.getOrderId())
        .flatMap(orderGetByIdUseCase::execute)
        .filter(order -> WAITING_FOR_PAYMENT.equals(order.getStatus()))
        .map(order -> Pair.of(PAID, order))
        .flatMap(orderSetStatusUseCase::execute)
        .flatMap(queueCreateUseCase::execute)
        .map(queue -> this.setQueueId(queue, charge));
  }

  private ChargeDTO setQueueId(QueueDTO queue, ChargeDTO charge) {
    charge.setQueueId(queue.getId());
    return charge;
  }
}
