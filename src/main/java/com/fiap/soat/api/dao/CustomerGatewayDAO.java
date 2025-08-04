package com.fiap.soat.api.dao;

import com.fiap.soat.api.dao.repository.CustomerRepository;
import com.fiap.soat.entities.dto.customer.CustomerDTO;
import com.fiap.soat.gateways.db.CustomerDatabaseGateway;
import com.fiap.soat.gateways.mapper.CustomerGatewayMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

@Component
@RequiredArgsConstructor
public class CustomerGatewayDAO implements CustomerDatabaseGateway {

  private final CustomerGatewayMapper mapper;
  private final CustomerRepository repository;

  @Override
  public Mono<CustomerDTO> findById(String id) {
    return this.stringToId(id).flatMap(repository::findById).map(mapper::toDTO);
  }

  @Override
  public Mono<CustomerDTO> save(CustomerDTO customer) {
    return Mono.just(customer).map(mapper::toDocument).flatMap(repository::save).map(mapper::toDTO);
  }

  @Override
  public Mono<CustomerDTO> getByDocumentNumber(String documentNumber) {
    return repository.findByDocumentNumber(documentNumber).map(mapper::toDTO);
  }

  @Override
  public Mono<Boolean> existsByDocumentNumber(String documentNumber) {
    return repository.existsByDocumentNumber(documentNumber);
  }
}
