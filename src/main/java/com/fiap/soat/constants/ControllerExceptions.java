package com.fiap.soat.constants;

import lombok.NoArgsConstructor;

import static lombok.AccessLevel.PRIVATE;

@NoArgsConstructor(access = PRIVATE)
public class ControllerExceptions {

    //Customer
    public static final String CUSTOMER_CREATE_DOCUMENT_NUMBER_REQUIRED = "CUSTOMER_CREATE_DOCUMENT_NUMBER_REQUIRED";
    public static final String CUSTOMER_CREATE_DOCUMENT_NUMBER_INVALID = "CUSTOMER_CREATE_DOCUMENT_NUMBER_INVALID";
    public static final String CUSTOMER_CREATE_NAME_REQUIRED = "CUSTOMER_CREATE_NAME_REQUIRED";
    public static final String CUSTOMER_EMAIL_REQUIRED = "CUSTOMER_EMAIL_REQUIRED";
    public static final String CUSTOMER_EMAIL_INVALID = "CUSTOMER_EMAIL_INVALID";

    //Product
    public static final String PRODUCT_CREATE_NAME_REQUIRED = "PRODUCT_CREATE_NAME_REQUIRED";
    public static final String PRODUCT_CREATE_CATEGORY_REQUIRED = "PRODUCT_CREATE_CATEGORY_REQUIRED";
    public static final String PRODUCT_CREATE_CATEGORY_INVALID = "PRODUCT_CREATE_CATEGORY_INVALID";
    public static final String PRODUCT_CREATE_AMOUNT_REQUIRED = "PRODUCT_CREATE_AMOUNT_REQUIRED";

}
