package com.fiap.soat.service;

import com.fiap.soat.model.dto.CustomerDTO;
import com.fiap.soat.repository.CustomerRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
@AllArgsConstructor
public class CustomerService {
  private CustomerRepository customerRepository;

  public Mono<CustomerDTO> create(CustomerDTO dto) {
    return Mono.empty();
  }
}
