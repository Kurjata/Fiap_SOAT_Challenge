package com.fiap.soat.constants;

import static lombok.AccessLevel.PRIVATE;

import lombok.NoArgsConstructor;

@NoArgsConstructor(access = PRIVATE)
public class Description {
    public final static String DOCUMENT_NUMBER_DESCRIPTION = "Document number in Brazilian CPF format";
    public final static String CUSTOMER_NAME_DESCRIPTION = "Customer name";

    //Exception
    public final static String EXCEPTION_RESPONSE_TIMESTAMP_DESCRIPTION = "Date time of exception";
    public final static String EXCEPTION_RESPONSE_ERROR_DESCRIPTION = "Description of thrown exception";
    public final static String EXCEPTION_RESPONSE_LIST_FIELD_DESCRIPTION = "List with description of fields that have an error";
    public final static String EXCEPTION_RESPONSE_HTTP_STATUS_DESCRIPTION = "Http exception status";
    public final static String EXCEPTION_FIELD_RESPONSE_NAME_DESCRIPTION = "Field name or exception validation";
    public final static String EXCEPTION_FIELD_RESPONSE_MESSAGE_DESCRIPTION = "Message describing the reason for the exception";
}
