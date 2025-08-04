package com.fiap.soat.controllers;


import com.fiap.soat.entities.dto.customer.CustomerDTO;
import com.fiap.soat.gateways.db.CustomerDatabaseGateway;
import com.fiap.soat.usecase.customer.CustomerCreateUseCase;
import com.fiap.soat.usecase.customer.CustomerGetByDocumentNumberUseCase;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
public class CustomerController {

  private final CustomerCreateUseCase createUseCase;
  private final CustomerGetByDocumentNumberUseCase getByDocumentNumberUseCase;

  public CustomerController(CustomerDatabaseGateway gateway) {
    this.createUseCase = new CustomerCreateUseCase(gateway);
    this.getByDocumentNumberUseCase = new CustomerGetByDocumentNumberUseCase(gateway);
  }

  public Mono<CustomerDTO> create(CustomerDTO customer) {
    return createUseCase.execute(customer);
  }

  public Mono<CustomerDTO> getByDocumentNumber(String documentNumber) {
    return getByDocumentNumberUseCase.execute(documentNumber);
  }
}
