package com.fiap.enums;

import lombok.Getter;

@Getter
public enum OrderStatus {
  CREATED,
  WAITING_FOR_PAYMENT,
  PAID,
  CANCELED
}
