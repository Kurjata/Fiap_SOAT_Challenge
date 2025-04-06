package com.fiap.soat.rest;

import static org.springframework.http.HttpStatus.CREATED;

import com.fiap.soat.constants.ExceptionSwagger;
import com.fiap.soat.mapper.CustomerMapper;
import com.fiap.soat.model.request.customer.CustomerCreateRequest;
import com.fiap.soat.model.response.customer.CustomerResponse;
import com.fiap.soat.service.CustomerService;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/api/v1/customer")
@Validated
@RequiredArgsConstructor
@ExceptionSwagger
@Tag(name = "Customer Controller", description = "Customer operations")
public class CustomerController {

  private final CustomerService customerService;
  private final CustomerMapper customerMapper;

  @PostMapping
  @ResponseStatus(CREATED)
  public Mono<CustomerResponse> create(@RequestBody @Valid final CustomerCreateRequest request) {

    return Mono.just(request)
        .map(customerMapper::toDTO)
        .flatMap(customerService::create)
        .map(customerMapper::toResponse);
  }
}
