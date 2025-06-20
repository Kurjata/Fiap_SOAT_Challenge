package com.fiap.service.impl;

import static com.fiap.enums.ServiceError.CUSTOMER_CREATE_EXISTS_DOCUMENT_NUMBER;
import static com.fiap.enums.ServiceError.CUSTOMER_NOT_EXISTS;
import static java.lang.Boolean.FALSE;


import com.fiap.dto.customer.Customer;
import com.fiap.exception.BusinessException;
import com.fiap.exception.NotFoundException;
import com.fiap.service.CustomerService;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;
import com.fiap.repository.CustomerRepository;

@Service
public class CustomerServiceImpl implements CustomerService {
  private final CustomerRepository customerRepository;

  public CustomerServiceImpl(CustomerRepository customerRepository) {
    this.customerRepository = customerRepository;
  }

  public Mono<Customer> create(Customer customer) {
    return customerRepository
        .existsByDocumentNumber(customer.getDocumentNumber())
        .filter(FALSE::equals)
        .switchIfEmpty(Mono.error(new BusinessException(CUSTOMER_CREATE_EXISTS_DOCUMENT_NUMBER)))
        .thenReturn(customer)
        .flatMap(customerRepository::save);
  }

  public Mono<Customer> getByDocumentNumber(String documentNumber) {
    return customerRepository
        .findByDocumentNumber(documentNumber)
        .switchIfEmpty(Mono.error(new NotFoundException(CUSTOMER_NOT_EXISTS)));
  }
}
