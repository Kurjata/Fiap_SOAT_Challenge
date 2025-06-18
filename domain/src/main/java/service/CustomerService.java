package service;

import dto.customer.Customer;
import reactor.core.publisher.Mono;

public interface CustomerService {
  Mono<Customer> create(Customer customer);

  Mono<Customer> getByDocumentNumber(String documentNumber);
}
