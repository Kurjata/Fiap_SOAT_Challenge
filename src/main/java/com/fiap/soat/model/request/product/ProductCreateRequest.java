package com.fiap.soat.model.request.product;

import com.fiap.soat.model.enums.ProductCategory;
import com.fiap.soat.rest.validation.ValueOfEnum;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

import static com.fiap.soat.constants.Description.PRODUCT_AMOUNT;
import static com.fiap.soat.constants.Description.PRODUCT_CATEGORY;
import static com.fiap.soat.constants.Description.PRODUCT_DESCRIPTION;
import static com.fiap.soat.constants.Description.PRODUCT_NAME;
import static com.fiap.soat.constants.Example.AMOUNT_EXAMPLE;
import static com.fiap.soat.constants.Example.PRODUCT_DESCRIPTION_EXAMPLE;
import static com.fiap.soat.constants.Example.PRODUCT_NAME_EXAMPLE;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ProductCreateRequest {
    @Schema(description = PRODUCT_NAME, example = PRODUCT_NAME_EXAMPLE)
    @NotBlank
    private String name;

    @Schema(description = PRODUCT_CATEGORY, implementation = ProductCategory.class)
    @ValueOfEnum(enumClass = ProductCategory.class, message = "")
    @NotBlank
    private String category;

    @Schema(description = PRODUCT_AMOUNT, example = AMOUNT_EXAMPLE)
    @NotNull
    private BigDecimal amount;

    @Schema(description = PRODUCT_DESCRIPTION, example = PRODUCT_DESCRIPTION_EXAMPLE)
    private String description;
}
