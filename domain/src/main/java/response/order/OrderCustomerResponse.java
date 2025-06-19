package response.order;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import static com.fiap.soat.constants.Description.CUSTOMER_EMAIL_DESCRIPTION;
import static com.fiap.soat.constants.Description.CUSTOMER_NAME_DESCRIPTION;
import static com.fiap.soat.constants.Description.DOCUMENT_NUMBER_DESCRIPTION;
import static com.fiap.soat.constants.Example.DOCUMENT_NUMBER_EXAMPLE;
import static com.fiap.soat.constants.Example.EMAIL_EXAMPLE;
import static com.fiap.soat.constants.Example.NAME_EXAMPLE;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(Include.NON_NULL)
public class OrderCustomerResponse {
    @Schema(description = DOCUMENT_NUMBER_DESCRIPTION, example = DOCUMENT_NUMBER_EXAMPLE)
    private String documentNumber;

    @Schema(description = CUSTOMER_NAME_DESCRIPTION, example = NAME_EXAMPLE)
    private String name;

    @Schema(description = CUSTOMER_EMAIL_DESCRIPTION, example = EMAIL_EXAMPLE)
    private String email;
}
