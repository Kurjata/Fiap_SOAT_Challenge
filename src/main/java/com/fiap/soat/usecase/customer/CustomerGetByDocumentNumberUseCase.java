package com.fiap.soat.usecase.customer;

import static com.fiap.soat.entities.enums.ServiceError.CUSTOMER_NOT_EXISTS;


import com.fiap.soat.entities.dto.customer.CustomerDTO;
import com.fiap.soat.entities.exception.NotFoundException;
import com.fiap.soat.gateways.db.CustomerDatabaseGateway;
import com.fiap.soat.usecase.UseCase;
import lombok.AllArgsConstructor;
import reactor.core.publisher.Mono;

@AllArgsConstructor
public class CustomerGetByDocumentNumberUseCase implements UseCase<String, CustomerDTO> {

  private final CustomerDatabaseGateway gateway;

  @Override
  public Mono<CustomerDTO> execute(String documentNumber) {
    return gateway
        .getByDocumentNumber(documentNumber)
        .switchIfEmpty(Mono.error(new NotFoundException(CUSTOMER_NOT_EXISTS)));
  }
}
