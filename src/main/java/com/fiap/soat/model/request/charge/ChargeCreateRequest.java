package com.fiap.soat.model.request.charge;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import static com.fiap.soat.constants.ControllerExceptions.ORDER_ID_REQUIRED;
import static com.fiap.soat.constants.Description.ORDER_ID_DESCRIPTION;
import static com.fiap.soat.constants.Example.ID_EXAMPLE;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ChargeCreateRequest {
    @NotBlank(message = ORDER_ID_REQUIRED)
    @Schema(description = ORDER_ID_DESCRIPTION, example = ID_EXAMPLE)
    private String orderId;
}
