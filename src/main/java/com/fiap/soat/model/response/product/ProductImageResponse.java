package com.fiap.soat.model.response.product;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.v3.oas.annotations.media.Schema;

import java.time.LocalDateTime;

import static com.fiap.soat.constants.DateConstants.DATE_TIME_PATTERN;
import static com.fiap.soat.constants.Description.PRODUCT_IMAGE_BYTES;
import static com.fiap.soat.constants.Description.PRODUCT_IMAGE_ID;
import static com.fiap.soat.constants.Description.PRODUCT_IMAGE_NAME;
import static com.fiap.soat.constants.Description.PRODUCT_IMAGE_TIMESTAMP_CREATED_DATE;
import static com.fiap.soat.constants.Example.DATE_TIME_EXAMPLE;
import static com.fiap.soat.constants.Example.ID_EXAMPLE;
import static com.fiap.soat.constants.Example.PRODUCT_IMAGE_NAME_EXAMPLE;

public class ProductImageResponse {
    @Schema(description = PRODUCT_IMAGE_ID, example = ID_EXAMPLE)
    private String id;

    @Schema(description = PRODUCT_IMAGE_TIMESTAMP_CREATED_DATE, example = DATE_TIME_EXAMPLE)
    @JsonFormat(pattern = DATE_TIME_PATTERN)
    private LocalDateTime timestampCreatedDate;

    @Schema(description = PRODUCT_IMAGE_NAME, example = PRODUCT_IMAGE_NAME_EXAMPLE)
    private String name;

    @Schema(description = PRODUCT_IMAGE_BYTES)
    private byte[] bytes;
}
