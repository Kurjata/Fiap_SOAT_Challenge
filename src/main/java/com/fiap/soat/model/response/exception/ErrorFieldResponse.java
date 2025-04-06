package com.fiap.soat.model.response.exception;

import static com.fiap.soat.constants.Description.EXCEPTION_FIELD_RESPONSE_MESSAGE_DESCRIPTION;
import static com.fiap.soat.constants.Description.EXCEPTION_FIELD_RESPONSE_NAME_DESCRIPTION;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ErrorFieldResponse {
    @Schema(description = EXCEPTION_FIELD_RESPONSE_NAME_DESCRIPTION)
    private String name;
    @Schema(description = EXCEPTION_FIELD_RESPONSE_MESSAGE_DESCRIPTION)
    private String message;
}
