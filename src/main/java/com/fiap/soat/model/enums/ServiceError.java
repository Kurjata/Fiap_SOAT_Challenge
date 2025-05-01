package com.fiap.soat.model.enums;

import lombok.Getter;

@Getter
public enum ServiceError {
    GENERIC_ERROR,
    CUSTOMER_CREATE_EXISTS_DOCUMENT_NUMBER,
    CUSTOMER_NOT_EXISTS,
    PRODUCT_NOT_EXISTS,
    ORDER_NOT_EXISTS,
    ORDER_PRODUCT_EXISTS_IN_LIST,
    ORDER_STATUS_MUST_BE_CREATED,
}
