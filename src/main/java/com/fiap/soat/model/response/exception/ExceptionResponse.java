package com.fiap.soat.model.response.exception;

import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

import static com.fasterxml.jackson.annotation.JsonInclude.Include.NON_NULL;
import static com.fiap.soat.constants.Description.EXCEPTION_RESPONSE_ERROR_DESCRIPTION;
import static com.fiap.soat.constants.Description.EXCEPTION_RESPONSE_HTTP_STATUS_DESCRIPTION;
import static com.fiap.soat.constants.Description.EXCEPTION_RESPONSE_LIST_FIELD_DESCRIPTION;
import static com.fiap.soat.constants.Description.EXCEPTION_RESPONSE_TIMESTAMP_DESCRIPTION;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(NON_NULL)
public class ExceptionResponse {
    @Schema(description = EXCEPTION_RESPONSE_TIMESTAMP_DESCRIPTION)
    private LocalDateTime timestamp;
    @Schema(description = EXCEPTION_RESPONSE_ERROR_DESCRIPTION)
    private String errorDescription;
    @Schema(description = EXCEPTION_RESPONSE_LIST_FIELD_DESCRIPTION)
    private List<ErrorFieldResponse> fields;
    @Schema(description = EXCEPTION_RESPONSE_HTTP_STATUS_DESCRIPTION)
    private Integer status;
}
