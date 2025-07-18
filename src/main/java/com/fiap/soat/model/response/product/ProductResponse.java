package com.fiap.soat.model.response.product;

import static com.fiap.soat.constants.Constants.DATE_TIME_PATTERN;
import static com.fiap.soat.constants.Description.PRODUCT_AMOUNT_DESCRIPTION;
import static com.fiap.soat.constants.Description.PRODUCT_CATEGORY_DESCRIPTION;
import static com.fiap.soat.constants.Description.PRODUCT_DESCRIPTION;
import static com.fiap.soat.constants.Description.PRODUCT_ID_DESCRIPTION;
import static com.fiap.soat.constants.Description.PRODUCT_NAME_DESCRIPTION;
import static com.fiap.soat.constants.Description.PRODUCT_TIMESTAMP_CREATE_DESCRIPTION;
import static com.fiap.soat.constants.Example.AMOUNT_EXAMPLE;
import static com.fiap.soat.constants.Example.DATE_TIME_EXAMPLE;
import static com.fiap.soat.constants.Example.ID_EXAMPLE;
import static com.fiap.soat.constants.Example.PRODUCT_DESCRIPTION_EXAMPLE;
import static com.fiap.soat.constants.Example.PRODUCT_NAME_EXAMPLE;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fiap.soat.model.enums.ProductCategory;
import io.swagger.v3.oas.annotations.media.Schema;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ProductResponse {
  @Schema(description = PRODUCT_ID_DESCRIPTION, example = ID_EXAMPLE)
  private String id;

  @Schema(description = PRODUCT_TIMESTAMP_CREATE_DESCRIPTION, example = DATE_TIME_EXAMPLE)
  @JsonFormat(pattern = DATE_TIME_PATTERN)
  private LocalDateTime timestampCreatedDate;

  @Schema(description = PRODUCT_CATEGORY_DESCRIPTION, implementation = ProductCategory.class)
  private ProductCategory category;

  @Schema(description = PRODUCT_NAME_DESCRIPTION, example = PRODUCT_NAME_EXAMPLE)
  private String name;

  @Schema(description = PRODUCT_DESCRIPTION, example = PRODUCT_DESCRIPTION_EXAMPLE)
  private String description;

  @Schema(description = PRODUCT_AMOUNT_DESCRIPTION, example = AMOUNT_EXAMPLE)
  private BigDecimal amount;

}
