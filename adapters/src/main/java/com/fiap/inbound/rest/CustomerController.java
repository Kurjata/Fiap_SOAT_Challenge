package com.fiap.inbound.rest;

import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.HttpStatus.OK;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;


import com.fiap.inbound.constants.ExceptionSwagger;
import com.fiap.inbound.mapper.CustomerMapper;
import com.fiap.inbound.model.request.customer.CustomerCreateRequest;
import com.fiap.inbound.model.response.customer.CustomerResponse;
import com.fiap.service.CustomerService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;
import util.CustomerUtil;

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
  @Operation(
      summary = "Customer creation",
      description = "This endpoint is used to create a new customer in the database.",
      responses =
          @ApiResponse(
              responseCode = "201",
              description = "Customer created",
              content =
                  @Content(
                      mediaType = APPLICATION_JSON_VALUE,
                      schema = @Schema(implementation = CustomerResponse.class))))
  public Mono<CustomerResponse> create(@RequestBody @Valid final CustomerCreateRequest request) {

    return Mono.just(request)
        .map(customerMapper::toCustomer)
        .flatMap(customerService::create)
        .map(customerMapper::toResponse);
  }

  @GetMapping("/{documentNumber}")
  @ResponseStatus(OK)
  @Operation(
      summary = "Customer get",
      description =
          "This endpoint is used to search for a customer in the database by document number.",
      responses =
          @ApiResponse(
              responseCode = "200",
              description = "Customer created",
              content =
                  @Content(
                      mediaType = APPLICATION_JSON_VALUE,
                      schema = @Schema(implementation = CustomerResponse.class))))
  public Mono<CustomerResponse> getByDocumentNumber(@PathVariable final String documentNumber) {
    return Mono.just(documentNumber)
        .map(CustomerUtil::clearDocumentNumber)
        .flatMap(customerService::getByDocumentNumber)
        .map(customerMapper::toResponse);
  }
}
