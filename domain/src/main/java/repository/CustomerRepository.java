package repository;

import dto.customer.Customer;
import reactor.core.publisher.Mono;

public interface CustomerRepository {
  Mono<Customer> save(Customer customer);

  Mono<Boolean> existsByDocumentNumber(String documentNumber);

  Mono<Customer> findByDocumentNumber(String documentNumber);
}
