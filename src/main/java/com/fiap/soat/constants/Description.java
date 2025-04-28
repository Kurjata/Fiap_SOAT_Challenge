package com.fiap.soat.constants;

import static lombok.AccessLevel.PRIVATE;

import lombok.NoArgsConstructor;

@NoArgsConstructor(access = PRIVATE)
public class Description {
  public static final String DOCUMENT_NUMBER_DESCRIPTION = "Document number in Brazilian CPF format";

  //Customer
  public static final String CUSTOMER_NAME_DESCRIPTION = "Customer name";
  public static final String CUSTOMER_TIMESTAMP_CREATE_DESCRIPTION = "Date time customer creation";
  public static final String CUSTOMER_EMAIL = "Customer email";

  //Product
  public static final String PRODUCT_ID = "Product id";
  public static final String PRODUCT_TIMESTAMP_CREATE_DESCRIPTION = "Date time product creation";
  public static final String PRODUCT_NAME = "Product name";
  public static final String PRODUCT_CATEGORY = "Product category";
  public static final String PRODUCT_AMOUNT = "Product amount";
  public static final String PRODUCT_DESCRIPTION = "Product description";
  public static final String PRODUCT_IMAGES = "Product image list";
  public static final String PRODUCT_IMAGE_ID = "Product image id";
  public static final String PRODUCT_IMAGE_TIMESTAMP_CREATED_DATE = "Product image timestamp created date";
  public static final String PRODUCT_IMAGE_NAME = "Product image file name";
  public static final String PRODUCT_IMAGE_BYTES = "Product image bytes array";

  // Exception
  public static final String EXCEPTION_RESPONSE_TIMESTAMP_DESCRIPTION = "Date time of exception";
  public static final String EXCEPTION_RESPONSE_ERROR_DESCRIPTION = "Description of thrown exception";
  public static final String EXCEPTION_RESPONSE_LIST_FIELD_DESCRIPTION = "List with description of fields that have an error";
  public static final String EXCEPTION_RESPONSE_HTTP_STATUS_DESCRIPTION = "Http exception status";
  public static final String EXCEPTION_FIELD_RESPONSE_NAME_DESCRIPTION = "Field name or exception validation";
  public static final String EXCEPTION_FIELD_RESPONSE_MESSAGE_DESCRIPTION = "Message describing the reason for the exception";

  //Page response
  public static final String PAGE_RESPONSE_PAGE_DESCRIPTION = "Page number";
  public static final String PAGE_RESPONSE_SIZE_DESCRIPTION = "Page size";
  public static final String PAGE_RESPONSE_ITEMS_DESCRIPTION = "List with searched items";
  public static final String PAGE_RESPONSE_TOTAL_PAGES_DESCRIPTION = "Total number of pages";
  public static final String PAGE_RESPONSE_HAS_NEXT_DESCRIPTION = "Check if there is a next page";
  public static final String PAGE_RESPONSE_IS_LAST_DESCRIPTION = "Check if it is the last page";

  //Page parameter
  public static final String PAGE_PARAMETER_DESCRIPTION = "Page number";
  public static final String PAGE_PARAMETER_SIZE_DESCRIPTION = "Page size";
}
