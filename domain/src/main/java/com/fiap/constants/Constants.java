package com.fiap.constants;

import static lombok.AccessLevel.PRIVATE;

import lombok.NoArgsConstructor;

@NoArgsConstructor(access = PRIVATE)
public class Constants {
  public static final String DATE_TIME_PATTERN = "yyyy-MM-dd'T'HH:mm:ss.SSS'Z'";
  public static final String DATE_PATTERN = "yyyy-MM-dd";
  public static final  String PAGE_DEFAULT = "0";
  public static final  String SIZE_DEFAULT = "20";
  public static final String SWAGGER_TYPE_DATE_TIME = "date-time";
}
