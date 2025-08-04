package com.fiap.soat.entities.enums;

import lombok.Getter;

@Getter
public enum OrderStatus {
  CREATED,
  WAITING_FOR_PAYMENT,
  PAID,
  CANCELED
}
