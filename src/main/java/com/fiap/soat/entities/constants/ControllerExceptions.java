package com.fiap.soat.entities.constants;

import lombok.NoArgsConstructor;

import static lombok.AccessLevel.PRIVATE;

@NoArgsConstructor(access = PRIVATE)
public class ControllerExceptions {

    public static final String DOCUMENT_NUMBER_INVALID = "DOCUMENT_NUMBER_INVALID";
    public static final String DATE_TIME_INVALID_FORMAT = "DATE_TIME_INVALID_FORMAT";

    //Customer
    public static final String CUSTOMER_CREATE_DOCUMENT_NUMBER_REQUIRED = "CUSTOMER_CREATE_DOCUMENT_NUMBER_REQUIRED";
    public static final String CUSTOMER_CREATE_NAME_REQUIRED = "CUSTOMER_CREATE_NAME_REQUIRED";
    public static final String CUSTOMER_EMAIL_REQUIRED = "CUSTOMER_EMAIL_REQUIRED";
    public static final String CUSTOMER_EMAIL_INVALID = "CUSTOMER_EMAIL_INVALID";

    //Product
    public static final String PRODUCT_CREATE_NAME_REQUIRED = "PRODUCT_CREATE_NAME_REQUIRED";
    public static final String PRODUCT_CREATE_CATEGORY_REQUIRED = "PRODUCT_CREATE_CATEGORY_REQUIRED";
    public static final String PRODUCT_CREATE_CATEGORY_INVALID = "PRODUCT_CREATE_CATEGORY_INVALID";
    public static final String PRODUCT_CREATE_AMOUNT_REQUIRED = "PRODUCT_CREATE_AMOUNT_REQUIRED";

    //ORDER
    public static final String ORDER_ID_REQUIRED = "ORDER_ID_REQUIRED";
    public static final String ORDER_ADD_PRODUCT_PRODUCT_ID_REQUIRED = "ORDER_ADD_PRODUCT_PRODUCT_ID_REQUIRED";
    public static final String ORDER_ADD_PRODUCT_QUANTITY_REQUIRED = "ORDER_ADD_PRODUCT_QUANTITY_REQUIRED";
    public static final String ORDER_ADD_PRODUCT_QUANTITY_MIN_ONE = "ORDER_ADD_PRODUCT_QUANTITY_MIN_ONE";
}
