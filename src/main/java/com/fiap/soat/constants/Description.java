package com.fiap.soat.constants;

import static lombok.AccessLevel.PRIVATE;

import lombok.NoArgsConstructor;

@NoArgsConstructor(access = PRIVATE)
public class Description {
  public static final String DOCUMENT_NUMBER_DESCRIPTION = "Document number in Brazilian CPF format";

  //Customer
  public static final String CUSTOMER_NAME_DESCRIPTION = "Customer name";
  public static final String CUSTOMER_TIMESTAMP_CREATE_DESCRIPTION = "Date time customer creation";

  // Exception
  public static final String EXCEPTION_RESPONSE_TIMESTAMP_DESCRIPTION = "Date time of exception";
  public static final String EXCEPTION_RESPONSE_ERROR_DESCRIPTION = "Description of thrown exception";
  public static final String EXCEPTION_RESPONSE_LIST_FIELD_DESCRIPTION = "List with description of fields that have an error";
  public static final String EXCEPTION_RESPONSE_HTTP_STATUS_DESCRIPTION = "Http exception status";
  public static final String EXCEPTION_FIELD_RESPONSE_NAME_DESCRIPTION = "Field name or exception validation";
  public static final String EXCEPTION_FIELD_RESPONSE_MESSAGE_DESCRIPTION = "Message describing the reason for the exception";
}
