package com.fiap.soat.model.request.customer;

import static com.fiap.soat.constants.ControllerExceptions.DOCUMENT_NUMBER_INVALID;
import static com.fiap.soat.constants.ControllerExceptions.CUSTOMER_CREATE_DOCUMENT_NUMBER_REQUIRED;
import static com.fiap.soat.constants.ControllerExceptions.CUSTOMER_CREATE_NAME_REQUIRED;
import static com.fiap.soat.constants.ControllerExceptions.CUSTOMER_EMAIL_INVALID;
import static com.fiap.soat.constants.ControllerExceptions.CUSTOMER_EMAIL_REQUIRED;
import static com.fiap.soat.constants.Description.CUSTOMER_EMAIL_DESCRIPTION;
import static com.fiap.soat.constants.Description.CUSTOMER_NAME_DESCRIPTION;
import static com.fiap.soat.constants.Description.DOCUMENT_NUMBER_DESCRIPTION;
import static com.fiap.soat.constants.Example.DOCUMENT_NUMBER_EXAMPLE;
import static com.fiap.soat.constants.Example.EMAIL_EXAMPLE;
import static com.fiap.soat.constants.Example.NAME_EXAMPLE;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.br.CPF;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CustomerCreateRequest {

  @Schema(description = DOCUMENT_NUMBER_DESCRIPTION, example = DOCUMENT_NUMBER_EXAMPLE)
  @NotBlank(message = CUSTOMER_CREATE_DOCUMENT_NUMBER_REQUIRED)
  @CPF(message = DOCUMENT_NUMBER_INVALID)
  private String documentNumber;

  @Schema(description = CUSTOMER_NAME_DESCRIPTION, example = NAME_EXAMPLE)
  @NotBlank(message = CUSTOMER_CREATE_NAME_REQUIRED)
  private String name;

  @Schema(description = CUSTOMER_EMAIL_DESCRIPTION, example = EMAIL_EXAMPLE)
  @NotBlank(message = CUSTOMER_EMAIL_REQUIRED)
  @Email(message = CUSTOMER_EMAIL_INVALID)
  private String email;
}
