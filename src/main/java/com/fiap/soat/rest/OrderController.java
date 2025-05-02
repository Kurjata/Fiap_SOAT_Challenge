package com.fiap.soat.rest;

import static com.fiap.soat.constants.Description.ORDER_ID_DESCRIPTION;
import static com.fiap.soat.constants.Description.PRODUCT_ID_DESCRIPTION;
import static com.fiap.soat.constants.Example.ID_EXAMPLE;
import static org.springframework.http.HttpStatus.ACCEPTED;
import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.HttpStatus.OK;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

import com.fiap.soat.constants.ExceptionSwagger;
import com.fiap.soat.mapper.OrderMapper;
import com.fiap.soat.model.request.order.OrderAddProductRequest;
import com.fiap.soat.model.request.order.OrderCreateRequest;
import com.fiap.soat.model.response.order.OrderResponse;
import com.fiap.soat.service.OrderService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/api/v1/order")
@Validated
@RequiredArgsConstructor
@ExceptionSwagger
@Tag(name = "Order Controller", description = "Order operations")
public class OrderController {
  private final OrderService orderService;
  private final OrderMapper orderMapper;

  @PostMapping
  @ResponseStatus(CREATED)
  @Operation(
      summary = "Order creation",
      description = "This endpoint is used to create a new order in the database.",
      responses =
          @ApiResponse(
              responseCode = "201",
              description = "Order created",
              content =
                  @Content(
                      mediaType = APPLICATION_JSON_VALUE,
                      schema = @Schema(implementation = OrderResponse.class))))
  public Mono<OrderResponse> create(@RequestBody @Valid final OrderCreateRequest request) {

    return Mono.just(request)
        .map(orderMapper::toDTO)
        .flatMap(orderService::save)
        .map(orderMapper::toResponse);
  }

  @PatchMapping("/addProduct")
  @ResponseStatus(OK)
  @Operation(
      summary = "Order add product",
      description = "This endpoint is used to add a product in the order.",
      responses =
          @ApiResponse(
              responseCode = "200",
              description = "Added product to order",
              content =
                  @Content(
                      mediaType = APPLICATION_JSON_VALUE,
                      schema = @Schema(implementation = OrderResponse.class))))
  public Mono<OrderResponse> addProduct(@RequestBody @Valid final OrderAddProductRequest request) {
    return Mono.just(request)
        .map(orderMapper::toDTO)
        .flatMap(orderService::addProduct)
        .map(orderMapper::toResponse);
  }

  @PatchMapping("/deleteProduct/order/{orderId}/product/{productId}")
  @ResponseStatus(OK)
  @Operation(
      summary = "Order remove product",
      description = "This endpoint is used to remove a product in the order.",
      responses =
          @ApiResponse(
              responseCode = "200",
              description = "Removed product from order.",
              content =
                  @Content(
                      mediaType = APPLICATION_JSON_VALUE,
                      schema = @Schema(implementation = OrderResponse.class))))
  public Mono<OrderResponse> removeProduct(
      @Parameter(description = ORDER_ID_DESCRIPTION, schema = @Schema(example = ID_EXAMPLE))
          @PathVariable
          final String orderId,
      @Parameter(description = PRODUCT_ID_DESCRIPTION, schema = @Schema(example = ID_EXAMPLE))
          @PathVariable
          final String productId) {
    return this.orderService.removeProduct(orderId, productId).map(orderMapper::toResponse);
  }

  @DeleteMapping
  @ResponseStatus(OK)
  @Operation(
          summary = "Cancel order",
          description = "This endpoint is used to cancel order.",
          responses =
          @ApiResponse(
                  responseCode = "200",
                  description = "Canceled order.",
                  content =
                  @Content(
                          mediaType = APPLICATION_JSON_VALUE,
                          schema = @Schema(implementation = OrderResponse.class))))
  public Mono<OrderResponse> cancel(
          @Parameter(description = ORDER_ID_DESCRIPTION, schema = @Schema(example = ID_EXAMPLE))
          @PathVariable
          final String orderId) {
      return this.orderService.cancel(orderId).map(orderMapper::toResponse);
  }

  // TODO: Criar chamada para criar pagamento (no primeiro momento só muda status pois não tem regra
  // de negócio)

  // TODO: listar pedidos

}
