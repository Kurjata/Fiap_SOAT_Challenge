package com.fiap.inbound.rest;


import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

import com.fiap.inbound.constants.ExceptionSwagger;
import request.charge.ChargeCreateRequest;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
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
@RequestMapping("/api/v1/charge")
@Validated
@RequiredArgsConstructor
@ExceptionSwagger
@Tag(name = "Charge Controller", description = "Charge operations")
public class ChargeController {
  private final ChargeService chargeService;
  private final ChargeMapper chargeMapper;

  @PostMapping
  @ResponseStatus(CREATED)
  @Operation(
      summary = "Charge creation",
      description =
          "This endpoint simulates the creation of a charge while there is no defined rule, it just changes the charge status to awaiting payment.",
      responses =
          @ApiResponse(
              responseCode = "201",
              description = "Charge created",
              content = @Content(mediaType = APPLICATION_JSON_VALUE)))
  public Mono<Void> create(@RequestBody @Valid ChargeCreateRequest request) {
    return Mono.just(request)
        .map(this.chargeMapper::toDTO)
        .flatMap(this.chargeService::create)
        .then();
  }

    @PostMapping("/payment")
    @ResponseStatus(CREATED)
    @Operation(
            summary = "Charge payment",
            description =
                    "This endpoint simulates the payment of a charge while there is no defined rule, it just changes the charge status to paid.",
            responses =
            @ApiResponse(
                    responseCode = "201",
                    description = "Charge created",
                    content = @Content(mediaType = APPLICATION_JSON_VALUE)))
    public Mono<Void> payment(@RequestBody @Valid ChargeCreateRequest request) {
        return Mono.just(request)
                .map(this.chargeMapper::toDTO)
                .flatMap(this.chargeService::payment)
                .then();
    }
}
