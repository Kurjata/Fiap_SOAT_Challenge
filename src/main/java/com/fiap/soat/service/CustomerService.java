package com.fiap.soat.service;

import static com.fiap.soat.model.enums.ServiceError.CUSTOMER_CREATE_EXISTS_DOCUMENT_NUMBER;
import static java.lang.Boolean.FALSE;
import static java.lang.Boolean.TRUE;

import com.fiap.soat.exception.BusinessException;
import com.fiap.soat.mapper.CustomerMapper;
import com.fiap.soat.model.dto.CustomerDTO;
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
}
