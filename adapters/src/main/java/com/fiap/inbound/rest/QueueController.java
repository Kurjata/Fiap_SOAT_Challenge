package com.fiap.inbound.rest;


import static com.fiap.constants.Constants.PAGE_DEFAULT;
import static com.fiap.constants.Constants.SIZE_DEFAULT;
import static com.fiap.constants.Constants.SWAGGER_TYPE_DATE_TIME;
import static com.fiap.constants.ControllerExceptions.DATE_TIME_INVALID_FORMAT;
import static com.fiap.constants.Description.PAGE_PARAMETER_DESCRIPTION;
import static com.fiap.constants.Description.PAGE_PARAMETER_SIZE_DESCRIPTION;
import static com.fiap.constants.Description.QUEUE_ID_DESCRIPTION;
import static com.fiap.constants.Description.QUEUE_STATUS_DESCRIPTION;
import static com.fiap.constants.Description.QUEUE_TIMESTAMP_CURRENT_STATUS_END_DESCRIPTION;
import static com.fiap.constants.Description.QUEUE_TIMESTAMP_CURRENT_STATUS_START_DESCRIPTION;
import static com.fiap.constants.Example.DATE_TIME_EXAMPLE;
import static com.fiap.constants.Example.ID_EXAMPLE;
import static org.springframework.http.HttpStatus.OK;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

import com.fiap.constants.ExceptionSwagger;
import com.fiap.inbound.mapper.QueueMapper;
import com.fiap.response.queue.QueuePageResponse;
import com.fiap.response.queue.QueueResponse;
import com.fiap.service.QueueService;
import com.fiap.validation.DateTimeFormat;
import com.fiap.validation.ValueOfEnum;
import com.fiap.enums.QueueTrackingStatus;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/api/v1/queue")
@Validated
@RequiredArgsConstructor
@ExceptionSwagger
@Tag(name = "Queue Controller", description = "Queue operations")
public class QueueController {
  private final QueueMapper queueMapper;
  private final QueueService queueService;

  @GetMapping
  @ResponseStatus(OK)
  @Operation(
      summary = "Queue get list",
      description = "This endpoint is used to get queues order by timestampCurrentStatus",
      responses =
          @ApiResponse(
              responseCode = "200",
              description = "Queue searched",
              content =
                  @Content(
                      mediaType = APPLICATION_JSON_VALUE,
                      schema = @Schema(implementation = QueuePageResponse.class))))
  public Mono<QueuePageResponse> getByFilter(
      @RequestParam(required = false, defaultValue = PAGE_DEFAULT)
          @Parameter(description = PAGE_PARAMETER_DESCRIPTION)
          final Integer page,
      @RequestParam(required = false, defaultValue = SIZE_DEFAULT)
          @Parameter(description = PAGE_PARAMETER_SIZE_DESCRIPTION)
          final Integer size,
      @RequestParam(required = false)
          @DateTimeFormat(message = DATE_TIME_INVALID_FORMAT)
          @Parameter(
              description = QUEUE_TIMESTAMP_CURRENT_STATUS_START_DESCRIPTION,
              schema = @Schema(example = DATE_TIME_EXAMPLE, type = SWAGGER_TYPE_DATE_TIME))
          final String startDate,
      @RequestParam(required = false)
          @DateTimeFormat(message = DATE_TIME_INVALID_FORMAT)
          @Parameter(
              description = QUEUE_TIMESTAMP_CURRENT_STATUS_END_DESCRIPTION,
              schema = @Schema(example = DATE_TIME_EXAMPLE, type = SWAGGER_TYPE_DATE_TIME))
          final String finalDate,
      @RequestParam(required = false)
          @ValueOfEnum(enumClass = QueueTrackingStatus.class)
          @Parameter(
              description = QUEUE_STATUS_DESCRIPTION,
              schema = @Schema(implementation = QueueTrackingStatus.class))
          final String status) {
    return Mono.just(this.queueMapper.toFilter(page, size, startDate, finalDate, status))
        .flatMap(this.queueService::getByFilter)
        .map(this.queueMapper::toPageResponse);
  }

  @PostMapping("/next-status/{id}")
  @ResponseStatus(OK)
  @Operation(
      summary = "Evolve queue record status",
      description = "This endpoint is intended to evolve the status of the queue record",
      responses =
          @ApiResponse(
              responseCode = "200",
              description = "Queue searched",
              content =
                  @Content(
                      mediaType = APPLICATION_JSON_VALUE,
                      schema = @Schema(implementation = QueueResponse.class))))
  public Mono<QueueResponse> nextStatus(
      @PathVariable
          @Parameter(description = QUEUE_ID_DESCRIPTION, schema = @Schema(example = ID_EXAMPLE))
          final String id) {
    return Mono.just(id).flatMap(this.queueService::nextStatus).map(this.queueMapper::toResponse);
  }
  // TODO: get by id
}
