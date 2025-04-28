package com.fiap.soat.model.enums;

import lombok.Getter;

@Getter
public enum OrderStatus {
  CREATED,
  WAITING_FOR_PAYMENT,
  PAID,
  CANCELED
}
