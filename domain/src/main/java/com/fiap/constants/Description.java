package com.fiap.constants;

import static lombok.AccessLevel.PRIVATE;

import lombok.NoArgsConstructor;

@NoArgsConstructor(access = PRIVATE)
public class Description {
  public static final String DOCUMENT_NUMBER_DESCRIPTION = "Document number in Brazilian CPF format";

  //Customer
  public static final String CUSTOMER_NAME_DESCRIPTION = "Customer name";
  public static final String CUSTOMER_TIMESTAMP_CREATE_DESCRIPTION = "Date time customer creation";
  public static final String CUSTOMER_EMAIL_DESCRIPTION = "Customer email";

  //Product
  public static final String PRODUCT_ID_DESCRIPTION = "Product id";
  public static final String PRODUCT_TIMESTAMP_CREATE_DESCRIPTION = "Date time product creation";
  public static final String PRODUCT_NAME_DESCRIPTION = "Product name";
  public static final String PRODUCT_CATEGORY_DESCRIPTION = "Product category";
  public static final String PRODUCT_AMOUNT_DESCRIPTION = "Product amount";
  public static final String PRODUCT_QUANTITY_DESCRIPTION = "Quantity of products";
  public static final String PRODUCT_DESCRIPTION = "Product description";


  //Order
  public static final String ORDER_ID_DESCRIPTION = "Order id";
  public static final String ORDER_TIMESTAMP_CREATE_DESCRIPTION = "Date time order creation";
  public static final String ORDER_TIMESTAMP_CREATE_START_DESCRIPTION = "Date time order creation initial search";
  public static final String ORDER_TIMESTAMP_CREATE_END_DESCRIPTION = "Date time order creation end search";
  public static final String ORDER_CUSTOMER_DESCRIPTION = "Order customer";
  public static final String ORDER_TOTAL_AMOUNT_DESCRIPTION = "Total amount of the order";
  public static final String ORDER_STATUS_DESCRIPTION = "Order status";
  public static final String ORDER_PRODUCTS_DESCRIPTION = "Order products";
  public static final String ORDER_PRODUCT_QUANTITY_DESCRIPTION = "Item quantity";

  //Queue
  public static final String QUEUE_ID_DESCRIPTION = "Queue id";
  public static final String QUEUE_STATUS_DESCRIPTION = "Queue status";
  public static final String QUEUE_TIMESTAMP_CURRENT_STATUS_START_DESCRIPTION = "Date time current status initial search";
  public static final String QUEUE_TIMESTAMP_CURRENT_STATUS_END_DESCRIPTION = "Date time current status end search";
  public static final String QUEUE_NICKNAME_DESCRIPTION = "Nickname for user identification in relation to the paid order";
  public static final String QUEUE_ITEMS_DESCRIPTION = "Items to be produced";
  public static final String QUEUE_ITEMS_NAME_DESCRIPTION = "Item name";
  public static final String QUEUE_ITEMS_QUANTITY_DESCRIPTION = "Item quantity";

  // Exception
  public static final String EXCEPTION_RESPONSE_TIMESTAMP_DESCRIPTION = "Date time of com.fiap.exception";
  public static final String EXCEPTION_RESPONSE_ERROR_DESCRIPTION = "Description of thrown com.fiap.exception";
  public static final String EXCEPTION_RESPONSE_LIST_FIELD_DESCRIPTION = "List with description of fields that have an error";
  public static final String EXCEPTION_RESPONSE_HTTP_STATUS_DESCRIPTION = "Http com.fiap.exception status";
  public static final String EXCEPTION_FIELD_RESPONSE_NAME_DESCRIPTION = "Field name or com.fiap.exception com.fiap.validation";
  public static final String EXCEPTION_FIELD_RESPONSE_MESSAGE_DESCRIPTION = "Message describing the reason for the com.fiap.exception";

  //Page com.fiap.response
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
