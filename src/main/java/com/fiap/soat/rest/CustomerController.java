package com.fiap.soat.rest;

import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

import com.fiap.soat.constants.ExceptionSwagger;
import com.fiap.soat.mapper.CustomerMapper;
import com.fiap.soat.model.request.customer.CustomerCreateRequest;
import com.fiap.soat.model.response.customer.CustomerResponse;
import com.fiap.soat.service.CustomerService;
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
        .map(customerMapper::toDTO)
        .flatMap(customerService::create)
        .map(customerMapper::toResponse);
  }

  @GetMapping("/{documentNumber}")
  @ResponseStatus(CREATED)
  @Operation(
          summary = "Customer get",
          description = "This endpoint is used to create a new customer in the database.",
          responses =
          @ApiResponse(
                  responseCode = "201",
                  description = "Customer created",
                  content =
                  @Content(
                          mediaType = APPLICATION_JSON_VALUE,
                          schema = @Schema(implementation = CustomerResponse.class))))
  public Mono<CustomerResponse> getByDocumentNumber(
          @PathVariable final String documentNumber
  ) {
    return Mono.empty();
  }
}
