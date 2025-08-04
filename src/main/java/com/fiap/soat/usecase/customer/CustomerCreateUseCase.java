package com.fiap.soat.usecase.customer;


import static com.fiap.soat.entities.enums.ServiceError.CUSTOMER_CREATE_EXISTS_DOCUMENT_NUMBER;
import static java.lang.Boolean.FALSE;



import com.fiap.soat.entities.dto.customer.CustomerDTO;
import com.fiap.soat.entities.exception.BusinessException;
import com.fiap.soat.gateways.db.CustomerDatabaseGateway;
import com.fiap.soat.usecase.UseCase;
import lombok.AllArgsConstructor;
import reactor.core.publisher.Mono;

@AllArgsConstructor
public class CustomerCreateUseCase implements UseCase<CustomerDTO, CustomerDTO> {
  private final CustomerDatabaseGateway gateway;

  @Override
  public Mono<CustomerDTO> execute(CustomerDTO customer) {
    return gateway
        .existsByDocumentNumber(customer.getDocumentNumber())
        .filter(FALSE::equals)
        .switchIfEmpty(Mono.error(new BusinessException(CUSTOMER_CREATE_EXISTS_DOCUMENT_NUMBER)))
        .thenReturn(customer)
        .flatMap(gateway::save);
  }
}
