package com.fiap.request.order;

import static com.fiap.constants.ControllerExceptions.DOCUMENT_NUMBER_INVALID;
import static com.fiap.constants.Description.DOCUMENT_NUMBER_DESCRIPTION;
import static com.fiap.constants.Example.DOCUMENT_NUMBER_EXAMPLE;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.br.CPF;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class OrderCreateRequest {
  @Schema(description = DOCUMENT_NUMBER_DESCRIPTION, example = DOCUMENT_NUMBER_EXAMPLE)
  @CPF(message = DOCUMENT_NUMBER_INVALID)
  private String documentNumber;
}
