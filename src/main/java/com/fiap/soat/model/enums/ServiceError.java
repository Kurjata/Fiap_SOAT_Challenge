package com.fiap.soat.model.enums;

import lombok.Getter;

@Getter
public enum ServiceError {
    GENERIC_ERROR,
    CUSTOMER_CREATE_EXISTS_DOCUMENT_NUMBER,
    CUSTOMER_NOT_EXISTS,
    PRODUCT_NOT_EXISTS
}
