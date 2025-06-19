package response.exception;

import static com.fasterxml.jackson.annotation.JsonInclude.Include.NON_NULL;
import static com.fiap.soat.constants.Constants.DATE_TIME_PATTERN;
import static com.fiap.soat.constants.Description.EXCEPTION_RESPONSE_ERROR_DESCRIPTION;
import static com.fiap.soat.constants.Description.EXCEPTION_RESPONSE_HTTP_STATUS_DESCRIPTION;
import static com.fiap.soat.constants.Description.EXCEPTION_RESPONSE_LIST_FIELD_DESCRIPTION;
import static com.fiap.soat.constants.Description.EXCEPTION_RESPONSE_TIMESTAMP_DESCRIPTION;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.v3.oas.annotations.media.Schema;
import java.time.LocalDateTime;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(NON_NULL)
public class ExceptionResponse {
  @Schema(description = EXCEPTION_RESPONSE_TIMESTAMP_DESCRIPTION)
  @JsonFormat(pattern = DATE_TIME_PATTERN)
  @Builder.Default
  private LocalDateTime timestamp = LocalDateTime.now();

  @Schema(description = EXCEPTION_RESPONSE_ERROR_DESCRIPTION)
  private String errorDescription;

  @Schema(description = EXCEPTION_RESPONSE_LIST_FIELD_DESCRIPTION)
  private List<ErrorFieldResponse> fields;

  @Schema(description = EXCEPTION_RESPONSE_HTTP_STATUS_DESCRIPTION)
  private Integer status;
}
