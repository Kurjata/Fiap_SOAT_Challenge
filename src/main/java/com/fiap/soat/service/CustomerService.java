package com.fiap.soat.service;

import static com.fiap.soat.model.enums.ServiceError.CUSTOMER_CREATE_EXISTS_DOCUMENT_NUMBER;
import static com.fiap.soat.model.enums.ServiceError.CUSTOMER_NOT_EXISTS;
import static java.lang.Boolean.FALSE;

import com.fiap.soat.exception.BusinessException;
import com.fiap.soat.exception.NotFoundException;
import com.fiap.soat.mapper.CustomerMapper;
import com.fiap.soat.model.dto.customer.CustomerDTO;
import com.fiap.soat.repository.CustomerRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
@AllArgsConstructor
public class CustomerService {
  private CustomerRepository customerRepository;
  private CustomerMapper customerMapper;

  public Mono<CustomerDTO> create(CustomerDTO dto) {
    return customerRepository
        .existsByDocumentNumber(dto.getDocumentNumber())
        .filter(FALSE::equals)
        .switchIfEmpty(Mono.error(new BusinessException(CUSTOMER_CREATE_EXISTS_DOCUMENT_NUMBER)))
        .thenReturn(dto)
        .flatMap(this::save);
  }

  private Mono<CustomerDTO> save(CustomerDTO dto) {
    return Mono.just(dto)
        .map(customerMapper::toDocument)
        .flatMap(customerRepository::save)
        .map(customerMapper::toDTO);
  }

  public Mono<CustomerDTO> getByDocumentNumber(String documentNumber) {
    return customerRepository
        .findByDocumentNumber(documentNumber)
        .switchIfEmpty(Mono.error(new NotFoundException(CUSTOMER_NOT_EXISTS)))
        .map(customerMapper::toDTO);
  }
}
