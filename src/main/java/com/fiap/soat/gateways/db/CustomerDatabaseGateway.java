package com.fiap.soat.gateways.db;

import com.fiap.soat.entities.dto.customer.CustomerDTO;
import reactor.core.publisher.Mono;

public interface CustomerDatabaseGateway extends DataBaseGateway<CustomerDTO> {
  Mono<CustomerDTO> getByDocumentNumber(String documentNumber);

  Mono<Boolean> existsByDocumentNumber(String documentNumber);
}
