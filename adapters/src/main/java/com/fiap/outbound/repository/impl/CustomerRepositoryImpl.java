package com.fiap.outbound.repository.impl;

import com.fiap.inbound.mapper.CustomerMapper;
import com.fiap.outbound.repository.MongoCustomerRepository;
import dto.customer.Customer;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;
import repository.CustomerRepository;

@Component
@RequiredArgsConstructor
public class CustomerRepositoryImpl extends BaseRepositoryImpl implements CustomerRepository {
  private final MongoCustomerRepository repository;
  private final CustomerMapper mapper;

  public Mono<Customer> save(Customer customer) {
    return Mono.just(customer)
        .map(mapper::toDocument)
        .flatMap(repository::save)
        .map(mapper::toCustomer);
  }

  public Mono<Boolean> existsByDocumentNumber(String documentNumber) {
    return repository.existsByDocumentNumber(documentNumber);
  }

  public Mono<Customer> findByDocumentNumber(String documentNumber) {
    return repository.findByDocumentNumber(documentNumber).map(mapper::toCustomer);
  }
}
