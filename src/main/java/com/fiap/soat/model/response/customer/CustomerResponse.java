package com.fiap.soat.model.response.customer;

import static com.fiap.soat.constants.DateConstants.DATE_TIME_PATTERN;
import static com.fiap.soat.constants.Description.CUSTOMER_NAME_DESCRIPTION;
import static com.fiap.soat.constants.Description.CUSTOMER_TIMESTAMP_CREATE_DESCRIPTION;
import static com.fiap.soat.constants.Description.DOCUMENT_NUMBER_DESCRIPTION;
import static com.fiap.soat.constants.Example.DATE_TIME_EXAMPLE;
import static com.fiap.soat.constants.Example.DOCUMENT_NUMBER_EXAMPLE;
import static com.fiap.soat.constants.Example.NAME_EXAMPLE;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.v3.oas.annotations.media.Schema;
import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CustomerResponse {

  @Schema(description = CUSTOMER_TIMESTAMP_CREATE_DESCRIPTION, example = DATE_TIME_EXAMPLE)
  @JsonFormat(pattern = DATE_TIME_PATTERN)
  private LocalDateTime timestampCreatedDate;

  @Schema(description = DOCUMENT_NUMBER_DESCRIPTION, example = DOCUMENT_NUMBER_EXAMPLE)
  private String documentNumber;

  @Schema(description = CUSTOMER_NAME_DESCRIPTION, example = NAME_EXAMPLE)
  private String name;
}
