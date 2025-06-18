package com.fiap.outbound.repository;

import com.fiap.inbound.mapper.CustomerMapper;
import dto.customer.Customer;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;
import repository.CustomerRepository;

@Component
@RequiredArgsConstructor
public class CustomerRepositoryImpl implements CustomerRepository {
  private final MongoCustomerRepository repository;
  private final CustomerMapper mapper;

  @Override
  public Mono<Customer> save(Customer customer) {
    return Mono.just(customer).map(mapper::toDocument).flatMap(repository::save).map(mapper::toCustomer);
  }

  @Override
  public Mono<Boolean> existsByDocumentNumber(String documentNumber) {
    return repository.existsByDocumentNumber(documentNumber);
  }

  @Override
  public Mono<Customer> findByDocumentNumber(String documentNumber) {
    return repository.findByDocumentNumber(documentNumber)
            .map(mapper::toCustomer);
  }
}
