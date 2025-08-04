package com.fiap.soat.api.web;

import static com.fiap.soat.entities.constants.Constants.PAGE_DEFAULT;
import static com.fiap.soat.entities.constants.Constants.SIZE_DEFAULT;
import static com.fiap.soat.entities.constants.Constants.SWAGGER_TYPE_DATE_TIME;
import static com.fiap.soat.entities.constants.ControllerExceptions.DATE_TIME_INVALID_FORMAT;
import static com.fiap.soat.entities.constants.ControllerExceptions.DOCUMENT_NUMBER_INVALID;
import static com.fiap.soat.entities.constants.Description.DOCUMENT_NUMBER_DESCRIPTION;
import static com.fiap.soat.entities.constants.Description.ORDER_ID_DESCRIPTION;
import static com.fiap.soat.entities.constants.Description.ORDER_STATUS_DESCRIPTION;
import static com.fiap.soat.entities.constants.Description.ORDER_TIMESTAMP_CREATE_END_DESCRIPTION;
import static com.fiap.soat.entities.constants.Description.ORDER_TIMESTAMP_CREATE_START_DESCRIPTION;
import static com.fiap.soat.entities.constants.Description.PAGE_PARAMETER_DESCRIPTION;
import static com.fiap.soat.entities.constants.Description.PAGE_PARAMETER_SIZE_DESCRIPTION;
import static com.fiap.soat.entities.constants.Description.PRODUCT_ID_DESCRIPTION;
import static com.fiap.soat.entities.constants.Example.DATE_TIME_EXAMPLE;
import static com.fiap.soat.entities.constants.Example.DOCUMENT_NUMBER_EXAMPLE;
import static com.fiap.soat.entities.constants.Example.ID_EXAMPLE;
import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.HttpStatus.OK;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

import com.fiap.soat.api.configuration.ExceptionSwagger;
import com.fiap.soat.api.web.validation.DateTimeFormat;
import com.fiap.soat.api.web.validation.ValueOfEnum;
import com.fiap.soat.controllers.OrderController;
import com.fiap.soat.entities.enums.OrderStatus;
import com.fiap.soat.entities.request.order.OrderAddProductRequest;
import com.fiap.soat.entities.request.order.OrderCreateRequest;
import com.fiap.soat.entities.response.order.OrderPageResponse;
import com.fiap.soat.entities.response.order.OrderResponse;
import com.fiap.soat.presenters.OrderPresenter;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.hibernate.validator.constraints.br.CPF;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/api/v1/order")
@Validated
@RequiredArgsConstructor
@ExceptionSwagger
@Tag(name = "Order Controller", description = "Order operations")
public class OrderApi {
  private final OrderController orderController;
  private final OrderPresenter orderPresenter;

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
        .map(orderPresenter::toDTO)
        .flatMap(orderController::save)
        .map(orderPresenter::toResponse);
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
        .map(orderPresenter::toDTO)
        .flatMap(orderController::addProduct)
        .map(orderPresenter::toResponse);
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
    return this.orderController.removeProduct(orderId, productId).map(orderPresenter::toResponse);
  }

  @DeleteMapping("/{orderId}")
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
    return this.orderController.cancel(orderId).map(orderPresenter::toResponse);
  }

  @GetMapping
  @ResponseStatus(OK)
  @Operation(
      summary = "Order get list ",
      description = "This endpoint is used to get orders",
      responses =
          @ApiResponse(
              responseCode = "200",
              description = "Orders searched",
              content =
                  @Content(
                      mediaType = APPLICATION_JSON_VALUE,
                      schema = @Schema(implementation = OrderPageResponse.class))))
  public Mono<OrderPageResponse> getByFilter(
      @RequestParam(required = false, defaultValue = PAGE_DEFAULT)
          @Parameter(description = PAGE_PARAMETER_DESCRIPTION)
          final Integer page,
      @RequestParam(required = false, defaultValue = SIZE_DEFAULT)
          @Parameter(description = PAGE_PARAMETER_SIZE_DESCRIPTION)
          final Integer size,
      @RequestParam(required = false)
          @Parameter(description = DOCUMENT_NUMBER_DESCRIPTION, example = DOCUMENT_NUMBER_EXAMPLE)
          @CPF(message = DOCUMENT_NUMBER_INVALID)
          final String documentNumber,
      @RequestParam(required = false)
          @DateTimeFormat(message = DATE_TIME_INVALID_FORMAT)
          @Parameter(
              description = ORDER_TIMESTAMP_CREATE_START_DESCRIPTION,
              schema = @Schema(example = DATE_TIME_EXAMPLE, type = SWAGGER_TYPE_DATE_TIME))
          final String startDate,
      @RequestParam(required = false)
          @DateTimeFormat(message = DATE_TIME_INVALID_FORMAT)
          @Parameter(
              description = ORDER_TIMESTAMP_CREATE_END_DESCRIPTION,
              schema = @Schema(example = DATE_TIME_EXAMPLE, type = SWAGGER_TYPE_DATE_TIME))
          final String finalDate,
      @RequestParam(required = false)
          @ValueOfEnum(enumClass = OrderStatus.class)
          @Parameter(
              description = ORDER_STATUS_DESCRIPTION,
              schema = @Schema(implementation = OrderStatus.class))
          final String status) {
    return Mono.just(
            this.orderPresenter.toFilter(page, size, documentNumber, startDate, finalDate, status))
        .flatMap(this.orderController::getByFilter)
        .map(this.orderPresenter::toPageResponse);
  }
}
