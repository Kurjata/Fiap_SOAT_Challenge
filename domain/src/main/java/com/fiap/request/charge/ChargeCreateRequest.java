package com.fiap.request.charge;

import static com.fiap.constants.ControllerExceptions.ORDER_ID_REQUIRED;
import static com.fiap.constants.Description.ORDER_ID_DESCRIPTION;
import static com.fiap.constants.Example.ID_EXAMPLE;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ChargeCreateRequest {
  @NotBlank(message = ORDER_ID_REQUIRED)
  @Schema(description = ORDER_ID_DESCRIPTION, example = ID_EXAMPLE)
  private String orderId;
}
