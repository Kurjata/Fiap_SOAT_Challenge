package com.fiap.service;

import com.fiap.dto.customer.Customer;
import reactor.core.publisher.Mono;

public interface CustomerService {
  Mono<Customer> create(Customer customer);

  Mono<Customer> getByDocumentNumber(String documentNumber);
}
